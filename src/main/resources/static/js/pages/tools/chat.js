let socket
	, nickname = '익명';

$(function() {
	setNickname();
});

function enterCheck() {
	if (event.keyCode == 13) {
		sendMsg();
	}
}

function sendMsg() {
	const msgEl = $('#msg')
		, content = msgEl.val().trim();
	if (content.length == 0 || content.length > 255) {
		return;
	}
	socket.send('[' + nickname + '] : ' + content);
	msgEl.val('');
}

function setWebSocketUrl() {
	let webSocketUrl = ''
		, webSocketProtocol = window.location.protocol == 'https:' ? 'wss' : 'ws';

	webSocketUrl = webSocketProtocol + '://' + window.location.hostname;

	if (window.location.port != '') {
		webSocketUrl += ':' + window.location.port
	}

	webSocketUrl += '/ws/chat/' + nickname;

	return webSocketUrl;
}

function setWebSocket() {
	socket = new WebSocket(setWebSocketUrl());

	socket.onopen = function (e) {
	};

	socket.onerror = function (e) {
		console.log(e);
	}

	socket.onmessage = function (e) {
		const msgDiv = $('#msgDiv')
			, totalCnt = e.data.split("|#|")[1]
			, serverNickname = e.data.split("|#|")[2];
		let msg = e.data.split("|#|")[0];

		$('#totalCnt').html(totalCnt);

		if (serverNickname === nickname) {
			msg = '<p><b>' + msg + '</b></p>';
		} else {
			msg = '<p>' + msg +'</p>';
		}
		msgDiv.prepend(msg);
	}

	socket.onclose = function (e) {
		setNickname('연결이 끊겼습니다.\n닉네임을 입력해주세요.');
	}
}

function setNickname(title) {
	if (title == null || title == 'null' || title == '' || title.length == 0) {
		title = '닉네임을 입력해주세요.';
	}

	swal(title, {
		content: 'input'
	}).then((value) => {
		const regex = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]+$/;
		if (value == null || value == 'null' || value == '' || value.length > 10 || !regex.test(value)) {
			setNickname('사용할 수 없습니다.\n다시 입력해주세요.');
		} else {
			nickname = value.trim();
			$('#nickname').html(nickname);
			setWebSocket();
		}
	});
}
