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
		, content = msgEl.val();
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
		socket.send('<span class="badge badge-success">[' + nickname + ']님이 입장했습니다.</span>');
	};

	socket.onerror = function (e) {
		console.log(e);
	}

	socket.onmessage = function (e) {
		const msgDiv = $('#msgDiv')
			, totalCnt = e.data.split("|#|")[1];
		let msg = e.data.split("|#|")[0];

		$('#totalCnt').html(totalCnt);

		if (msg.indexOf(nickname) > -1) {
			msg = '<p><b>' + msg + '</b></p>';
		} else {
			msg = '<p>' + msg +'</p>';
		}
		msgDiv.prepend(msg);
	}

	socket.onclose = function (e) {
		setNickname();
	}
}

function setNickname() {
	swal('닉네임을 입력해주세요.', {
		content: 'input',
	}).then((value) => {
		if (value == null || value == 'null' || value == '' || value.length > 10) {
			setNickname();
		} else {
			nickname = value;
			$('#nickname').html(nickname);
			setWebSocket();
		}
	});
}
