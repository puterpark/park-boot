Mousetrap.bind('ctrl+enter',function(){predict();});

$(function() {
	var $original = $('#ta');
	$original.focusin(function() {
		$('label[for="ta"]').addClass('hidden');
	});

	$original.focusout(function() {
		if ($original.val().trim().length == 0) {
			$('label[for="ta"]').removeClass('hidden');
		}
	});
});

function predict() {
	var ta = $('#ta').val().trim();
	
	if (ta.length < 1) {
		swal('입력된 값이 없습니다.', '', 'error');
		return;
	}
	
	var imgPath = '/resources/images/pages/tools/';
	
	loader(1);
	
	$.ajax({
		url : '/tools/predict',
		type: 'post',
		dataType: 'json',
		data : {
			text : ta
		},
		success: function (data) {
			if (data.result) {
				var title = 'Negative!',
					fileName = 'negative';
				
				if (data.mood) {
					title = 'Positive!';
					fileName = 'positive';
				}
				
				swal({
					title: title,
					text: ta,
					icon: imgPath + fileName + '.png'
				});
			}
		},
		error: function (request, status, error) {
		},
		complete: function () {
			loader(0);
		}
	});
}