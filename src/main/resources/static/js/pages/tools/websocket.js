const socket = new WebSocket('wss://' + window.location.hostname + ':' + window.location.port + '/test/websocket');

socket.onopen = function (e) {
	console.log('open server.');
};

socket.onerror = function (e) {
	console.log(e);
}

socket.onmessage = function (e) {
	const msgDiv = $('#msgDiv');
	msgDiv.append('<p>' + e.data +'</p>')
}

function enterCheck() {
	if (event.keyCode == 13) {
		sendMsg();
	}
}

function sendMsg() {
	const content = $('#original').val();
	socket.send(content);
}

