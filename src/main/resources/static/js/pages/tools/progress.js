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
			$('#result-badge').removeClass('hidden');

			const rate = calculateRate(startDate, baseDate, endDate);
			$('#resultDiv').append(renderProgress(rate));
		}
	});
});

/**
 * 입력 날짜 유효성 체크
 * @param {string} startDate
 * @param {string} baseDate
 * @param {string} endDate
 * @returns {boolean} 유효성 여부
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
 * @param {string} startDate
 * @param {string} baseDate
 * @param {string} endDate
 * @returns {number} 진척률
 */
function calculateRate(startDate, baseDate, endDate) {
	const totalCount = ((new Date(endDate) - new Date(startDate)) / 86400000) + 1;
	const progressCount = ((new Date(baseDate) - new Date(startDate)) / 86400000) + 1;
	const rate = Math.floor((progressCount / totalCount) * 100);

	return rate;
}

/**
 * proress bar 설정
 * @param rate
 * @returns {string} progress 요소
 */
function renderProgress(rate) {
	let progressEl = '<div class="progress">' +
							'<div class="progress-bar progress-bar-striped progress-bar-animated bg-secondary" role="progressbar"' +
							'aria-valuemin="0" aria-valuemax="100" aria-valuenow="[$RATE$]" style="width: [$RATE$]%">[$RATE$]%</div>' +
						'</div>';
	progressEl = progressEl.replaceAll('\[$RATE$\]', rate);

	return progressEl;
}
