Mousetrap.bind('ctrl+enter',function(){shorten();});

function enterCheck() {
	if (event.keyCode == 13) {
		shorten();
	}
}

function shorten() {
	var originalUrl = $('#originalUrl').val().trim();
	
	$('#resultDiv').addClass('hidden');
	
	if (originalUrl.length < 1) {
		swal('입력된 값이 없습니다.', '', 'error');
		return;
	}
	
	var regExr = /^(https?:\/\/)([\w\d-_]+)\.([\w\d-_\.]+)\/?\??([^#\n\r]*)?#?([^\n\r]*)/;
	if (!regExr.test(originalUrl)) {
		swal('URL 형식이 아닙니다.', '', 'error');
		return;
	}
	
	loader(1);
	
	$.ajax({
		url : '/tools/shorten-url',
		type: 'post',
		dataType: 'json',
		data : {
			originalUrl : originalUrl
		},
		success: function (data) {
			if (data.result) {
				var shortenUrl = window.location.protocol + '//' + window.location.hostname;
				if (window.location.port != '') {
					shortenUrl += ':' + window.location.port;
				}
				shortenUrl += '/' + data.shortenUri;
				$('#resultDiv').empty().append("<div class='widget widget5 card' onclick='javascript:copy();'><div class='widget-content p-4'><div class='row'><div class='col-12' style='display: flex;'><div class='col' style='padding-top: 10px;'><h4 id='shortenUrl'>" + shortenUrl + "</h4></div></div></div></div></div>");
				$('#resultDiv').removeClass('hidden');
			}
		},
		error: function (request, status, error) {
		},
		complete : function() {
			loader(0);
		}
	});
}

function copy() {
	$('#tmpForCopy').val($('#shortenUrl').text());
	$('#tmpForCopy').select();
	document.execCommand('copy');
	swal('복사되었습니다.', '', 'success');
}