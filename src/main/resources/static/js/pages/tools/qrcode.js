$(function() {
	var $original = $('#textarea');
	$original.focusin(function() {
		$('label[for="textarea"]').addClass('hidden');
	});

	$original.focusout(function() {
		if ($original.val().trim().length == 0) {
			$('label[for="textarea"]').removeClass('hidden');
		}
	});
});

var $qrCode = document.getElementById('qrCode'),
	qrCodeSize = $('#qrCodeSize').val();
	
$qrCode.setAttribute('class', 'hidden');

var qrCode = new QRCode($qrCode, {
	width : qrCodeSize,
	height : qrCodeSize
});

function makeCode() {
	var val = $('#textarea').val().trim();

	$('#result-badge').removeClass('hidden');
	$qrCode.setAttribute('class', 'hidden');
	
	if (val.length > 0) {
		qrCode.makeCode(val);
		$qrCode.setAttribute('class', '');
	}
}
