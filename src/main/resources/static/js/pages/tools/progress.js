$(function() {
	$('input[type="date"]').on('change', function() {
		const $startDate = $('#startDate')
			, $baseDate = $('#baseDate')
			, $endDate = $('#endDate')
			, startDate = $startDate.val()
			, baseDate = $baseDate.val()
			, endDate = $endDate.val();

		$('#resultDiv').empty();

		if (validate(startDate, baseDate, endDate)) {
			let rate = calculateRate(startDate, baseDate, endDate);
			$('#resultDiv').append(rate + '%');
		}
	});
});

/**
 * 입력 날짜 유효성 체크
 * @param startDate
 * @param baseDate
 * @param endDate
 * @returns {boolean}
 */
function validate(startDate, baseDate, endDate) {
	let isValid = false;

	if (startDate == '' || startDate.length < 1) {
		return isValid;
	}

	if (baseDate == '' || baseDate.length < 1) {
		return isValid;
	}

	if (endDate == '' || endDate.length < 1) {
		return isValid;
	}

	if (!(startDate <= baseDate && startDate <= endDate)) {
		return isValid;
	}

	if (!(baseDate <= endDate)) {
		return isValid;
	}

	isValid = true;
	return isValid;
}

/**
 * 시작일/기준일/종료일을 이용한 진척률 계산
 * @param startDate
 * @param baseDate
 * @param endDate
 * @returns {number}
 */
function calculateRate(startDate, baseDate, endDate) {
	const totalCount = ((new Date(endDate) - new Date(startDate)) / 86400000) + 1;
	const progressCount = ((new Date(baseDate) - new Date(startDate)) / 86400000) + 1;
	const rate = Math.floor((progressCount / totalCount) * 100);

	return rate;
}