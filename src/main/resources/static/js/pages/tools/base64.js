Mousetrap.bind('ctrl+enter',function(){convert(1);});
Mousetrap.bind('ctrl+shift+enter',function(){convert(2);});

function convert(mode) {
	var val = $('#textarea').val().trim();
	var result = '';

	if (val == '') {
		swal('입력된 값이 없습니다.', '', 'error');
		return;
	}

	if (mode == 1) {
		result = Base64.encode(val);
	} else if (mode == 2) {
		var regExp = /^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$/;
		if (!regExp.test(val)) {
			swal('다시 입력해주세요.', '입력된 값을 복호화할 수 없습니다.', 'error');
			return;
		}
		result = Base64.decode(val);
		if (!result) {
			swal('입력된 값이 잘못되었습니다.', '', 'error');
			return;
		}
	}

	$('#resultDiv').empty().append("<div class='widget widget5 card'><div class='widget-content p-4'><div class='row'><div class='col-12 col-lg-6'><div id='result' class='form-group' style='display: flex;'></div></div><div class='col-12 col-lg-6'><h4 id='copy'></h4></div></div></div></div>");
	$('#result').empty().append("<textarea class='form-control' id='output' rows='10' onclick='javascript:copy();'>"+ result +"</textarea>");
	$('#copy').empty().append('결과를 클릭하면 복사됩니다.');
	$('#resultDiv').removeClass('hidden');

	document.getElementById('resultDiv').scrollIntoView();
}

function copy() {
	$('#output').select();
	document.execCommand('copy');
	swal('복사되었습니다.', '', 'success');
}

var Base64 = {
		// private property
		_keyStr : "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
	
		// public method for encoding
		encode : function (input) {
			var output = "";
			var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
			var i = 0;
	
			input = Base64._utf8_encode(input);
	
			while (i < input.length) {
	
				chr1 = input.charCodeAt(i++);
				chr2 = input.charCodeAt(i++);
				chr3 = input.charCodeAt(i++);
	
				enc1 = chr1 >> 2;
				enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
				enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
				enc4 = chr3 & 63;
	
				if (isNaN(chr2)) {
					enc3 = enc4 = 64;
				} else if (isNaN(chr3)) {
					enc4 = 64;
				}
	
				output = output +
				this._keyStr.charAt(enc1) + this._keyStr.charAt(enc2) +
				this._keyStr.charAt(enc3) + this._keyStr.charAt(enc4);
	
			}
	
			return output;
		},
	
		// public method for decoding
		decode : function (input) {
			var output = "";
			var chr1, chr2, chr3;
			var enc1, enc2, enc3, enc4;
			var i = 0;
	
			input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
	
			while (i < input.length) {
	
				enc1 = this._keyStr.indexOf(input.charAt(i++));
				enc2 = this._keyStr.indexOf(input.charAt(i++));
				enc3 = this._keyStr.indexOf(input.charAt(i++));
				enc4 = this._keyStr.indexOf(input.charAt(i++));
	
				chr1 = (enc1 << 2) | (enc2 >> 4);
				chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
				chr3 = ((enc3 & 3) << 6) | enc4;
	
				output = output + String.fromCharCode(chr1);
	
				if (enc3 != 64) {
					output = output + String.fromCharCode(chr2);
				}
				if (enc4 != 64) {
					output = output + String.fromCharCode(chr3);
				}
	
			}
	
			output = Base64._utf8_decode(output);
	
			return output;
	
		},
	
		// private method for UTF-8 encoding
		_utf8_encode : function (string) {
			string = string.replace(/\r\n/g,"\n");
			var utftext = "";
	
			for (var n = 0; n < string.length; n++) {
	
				var c = string.charCodeAt(n);
	
				if (c < 128) {
					utftext += String.fromCharCode(c);
				}
				else if((c > 127) && (c < 2048)) {
					utftext += String.fromCharCode((c >> 6) | 192);
					utftext += String.fromCharCode((c & 63) | 128);
				}
				else {
					utftext += String.fromCharCode((c >> 12) | 224);
					utftext += String.fromCharCode(((c >> 6) & 63) | 128);
					utftext += String.fromCharCode((c & 63) | 128);
				}
	
			}
	
			return utftext;
		},
	
		// private method for UTF-8 decoding
		_utf8_decode : function (utftext) {
			var string = "";
			var i = 0;
			var c = c1 = c2 = 0;
	
			while ( i < utftext.length ) {
	
				c = utftext.charCodeAt(i);
	
				if (c < 128) {
					string += String.fromCharCode(c);
					i++;
				}
				else if((c > 191) && (c < 224)) {
					c2 = utftext.charCodeAt(i+1);
					string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
					i += 2;
				}
				else {
					c2 = utftext.charCodeAt(i+1);
					c3 = utftext.charCodeAt(i+2);
					string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
					i += 3;
				}
	
			}
	
			return string;
		}
	}