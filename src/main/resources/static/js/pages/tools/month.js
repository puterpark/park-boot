Mousetrap.bind('ctrl+enter',function(){convert();});

function convert() {
	var val = $('#textarea').val().trim();

	if (val.length == '') {
		swal('입력된 값이 없습니다.', '', 'error');
		return;
	}

	var lines = val.split('\n'),
		line = '',
		projectArr = new Array(),
		resource1 = 0,
		resource2 = 0,
		resourceArr = new Array(),
		detailArr = new Array();

	// 한 줄씩 읽어서 배열에 넣음
	for (var i = 0; i < lines.length; i++) {
		line = lines[i];
		
		var split = line.split(' / ');
		if (split[1] == null || split[1].trim() == '' || isNaN(split[1].trim())) {
			swal('다시 입력해주세요.', '입력된 값이 우측의 형식과 다릅니다.', 'error');
			return;
		}
		detailArr.push(split[0].trim());
		resourceArr.push(split[1].trim());
		if (line.indexOf('(비상주)') > -1) {
			resource2 += parseFloat(split[1].trim());
		} else {
			resource1 += parseFloat(split[1].trim());
		}
	}
	
	var dupArr = new Array();

	// 상세사항의 공수 중복처리, 중복일 경우 index를 dupArr에 넣는다.
	detailArr = detailArr.reduce(function(iv, cv, ci){
		var dupIndex = iv.indexOf(cv);

		if (dupIndex < 0) {
			iv.push(cv);
		} else {
			iv.push(cv);
			resourceArr[dupIndex] += '+' + resourceArr[ci];
			dupArr.push(ci);
		}
		return iv;
	},[])

	// dupArr에 담긴 값을 뒤에서부터 삭제 (중복 제거)
	for (var i = dupArr.length - 1; i > -1; i--) {
		resourceArr.splice(dupArr[i], 1);
		detailArr.splice(dupArr[i], 1);
	}

	// 중복된 상세사항의 공수 덧셈 처리
	for (var i = 0; i < resourceArr.length; i++) {
		if (resourceArr[i].indexOf('+') > -1) {
			var splitResource = resourceArr[i].split('+');
			var resourceSum = 0;
			for (var j = 0; j < splitResource.length; j++) {
				resourceSum += parseFloat(splitResource[j]);
			}
			resourceArr[i] += '=' + resourceSum;
		}
	}

	var result1 = '',
		result2 = '';

	/*
	 * 상세사항에 "(비상주)"가 있을 경우 result2에 할당
	 * 그 외에는 result1로 할당
	 */
	for (var i = 0; i < detailArr.length; i++) {
		var finalDetail = detailArr[i],
			finalResource = resourceArr[i];
		
		if (finalDetail.indexOf('(비상주)') > -1) {
			result2 += '\n' + finalDetail + ' / ' + finalResource;
		} else {
			result1 += '\n' + finalDetail + ' / ' + finalResource;
		}
	}

	var resultDiv = '';
	resultDiv += "<div class='widget widget5 card'>";
	resultDiv += 	"<div class='widget-content p-4'>";
	resultDiv +=		"<div class='row'>";
	resultDiv +=			"<div class='col-12 col-lg-6'>";
	resultDiv +=				"투입공수 : <input type='text' id='resource1' onclick='javascript:copy(this);' value='" + resource1 + "' />";
	resultDiv +=				"<div id='result1' class='form-group' style='display: flex;'></div>";
	resultDiv +=			"</div>";
	resultDiv +=			"<div class='col-12 col-lg-6'>";
	if (result2.trim().length > 0) {
		// 상세사항 중 (비상주)가 있을 경우 비상주 내역을 따로 보여준다.
		resultDiv +=			"투입공수 : <input type='text' id='resource2' onclick='javascript:copy(this);' value='" + resource2 + "' />";
		resultDiv +=			"<div id='result2' class='form-group' style='display: flex;'></div>";
	} else {
		resultDiv +=			"<div class='alert alert-success' role='alert' id='copy'></div>";
	}
	resultDiv +=			"</div>";
	resultDiv +=		"</div>";
	resultDiv +=	"</div>";
	resultDiv += "</div>";
	$('#resultDiv').empty().append(resultDiv);
	
	$('#result1').empty().append("<textarea class='form-control' id='output1' rows='15' onclick='javascript:copy(this);'>"+ result1 +"</textarea>");
	if (result2.trim().length > 0) {
		$('#result2').empty().append("<textarea class='form-control' id='output2' rows='15' onclick='javascript:copy(this);'>"+ result2 +"</textarea>");
	} else {
		$('#copy').empty().append("<h5 class='alert-heading'>결과를 클릭하면 복사됩니다.</h5>");
	}
	$('#resultDiv').removeClass('hidden');

	document.getElementById('resultDiv').scrollIntoView();
}

function copy(t) {
	var val = $('#' + t.id).val();
	if (val.trim().length == 0) {
		return;
	}
	t.select();
	document.execCommand('copy');
	swal('복사되었습니다.', '', 'success');
}