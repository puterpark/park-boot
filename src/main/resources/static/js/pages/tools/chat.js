let socket = new WebSocket(setWebSocketUrl());

socket.onopen = function (e) {
	console.log('open server.');
};

socket.onerror = function (e) {
	console.log(e);
}

socket.onmessage = function (e) {
	const msgDiv = $('#msgDiv');
	msgDiv.prepend('<p>' + e.data +'</p>')
}

socket.onclose = function (e) {
	console.log('close server.');
}

function enterCheck() {
	if (event.keyCode == 13) {
		sendMsg();
	}
}

function sendMsg() {
	const content = $('#original').val();
	socket.send(content);
	$('#original').val('');
}

function setWebSocketUrl() {
	let webSocketUrl = ''
		, webSocketProtocol = window.location.protocol == 'https' ? 'wss' : 'ws';

	webSocketUrl = webSocketProtocol + '://' + window.location.hostname;

	if (window.location.port != '80' || window.location.port != 443) {
		webSocketUrl += ':' + window.location.port
	}

	webSocketUrl += '/ws/chat';

	return webSocketUrl;
}

