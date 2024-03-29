Mousetrap.bind('ctrl+enter',function(){convert();});

function convert() {
	var val = $('#textarea').val().trim();

	if (val == '') {
		swal('입력된 값이 없습니다.', '', 'error');
		return;
	}

	var lines = val.split('\n');
	var line = '';
	var projectArr = new Array();
	var resource = 0;
	var resourceArr = new Array();
	var detailArr = new Array();

	for (var i = 0; i < lines.length; i++) {
		line = lines[i];

		if (!line.trim().startsWith('>')) {
			if (resource > 0) {
				resourceArr.push(resource);
				resource = 0;
			}
			detailArr.push(line);
		} else {
			var split = line.split(' / ');
			if (split[1] == null || split[1].trim() == '' || isNaN(split[1].trim())) {
				swal('다시 입력해주세요.', '입력된 값이 우측의 형식과 다릅니다.', 'error');
				return;
			}
			resource += parseFloat(split[1].trim());
			detailArr.push(split[0].trim());
		}

		if (i == lines.length - 1) {
			resourceArr.push(resource);
		}
	}
	
	//중복제거
	detailArr = detailArr.reduce(function(a, b){
		if (a.indexOf(b) < 0) {
			a.push(b);
		}
		return a;
	},[])

	for (var i = 0; i < detailArr.length; i++) {
		line = detailArr[i];

		if (!line.trim().startsWith('>')) {
			projectArr.push(i);
		}
	}

	var result = '';
	var cnt = 0;
	resource = 0;

	for (var i = 0; i < detailArr.length; i++) {
		if (projectArr.includes(i)) {
			result += '\n- ' + detailArr[i] + ' / ' + resourceArr[cnt] + 'd';
			resource += parseFloat(resourceArr[cnt]);
			cnt++;
		} else {
			result += '\n ' + detailArr[i];
		}
	}

	$('#resultDiv').empty().append("투입공수 : "+ resource +"<div class='widget widget5 card'><div class='widget-content p-4'><div class='row'><div class='col-12 col-lg-6'><div id='result' class='form-group' style='display: flex;'></div></div><div class='col-12 col-lg-6' id='copy'></div></div></div></div>");
	$('#result').empty().append("<textarea class='form-control' id='output' rows='15' onclick='javascript:copy();'>"+ result +"</textarea>");
	$('#copy').empty().append("<div class='alert alert-success' role='alert'><h5 class='alert-heading'>결과를 클릭하면 복사됩니다.</h5></div>");
	$('#resultDiv').removeClass('hidden');

	document.getElementById('resultDiv').scrollIntoView();
}

function copy() {
	$('#output').select();
	document.execCommand('copy');
	swal('복사되었습니다.', '', 'success');
}