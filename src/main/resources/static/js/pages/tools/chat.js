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

	webSocketUrl += '/ws/chat';

	return webSocketUrl;
}

function setWebSocket() {
	socket = new WebSocket(setWebSocketUrl());

	socket.onopen = function (e) {
		console.log('open server.');
		socket.send(nickname + ' 입장!');
	};

	socket.onerror = function (e) {
		console.log(e);
	}

	socket.onmessage = function (e) {
		const msgDiv = $('#msgDiv');
		let msg = e.data;

		if (msg.indexOf(nickname) > -1) {
			msg = '<p><b>' + msg +'</b></p>';
		} else {
			msg = '<p>' + msg +'</p>';
		}
		msgDiv.prepend(msg);
	}

	socket.onclose = function (e) {
		console.log('close server.');
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
			setWebSocket();
		}
	});
}
