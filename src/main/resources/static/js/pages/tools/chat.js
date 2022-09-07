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
	const content = $('#original').val();
	if (content.length == 0) {
		return;
	}
	socket.send(nickname + ' : ' + content);
	$('#original').val('');
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

function setWebSocket(isReopen) {
	socket = new WebSocket(setWebSocketUrl());

	socket.onopen = function (e) {
		if (!isReopen) {
			socket.send('(입장) ' + nickname);
		}
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
			msg = '<p><b>' + msg +'</b></p>';
		} else {
			msg = '<p>' + msg +'</p>';
		}
		msgDiv.prepend(msg);
	}

	socket.onclose = function (e) {
		setWebSocket(true);
	}
}

function setNickname() {
	swal('닉네임을 입력해주세요.', {
		content: 'input',
	}).then((value) => {
		if (value == null || value == 'null' || value == '') {
			setNickname();
		} else {
			nickname = value;
			$('#nickname').html(nickname);
			setWebSocket();
		}
	});
}
