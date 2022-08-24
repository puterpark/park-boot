$(function () {

});

/**
 * 시작일 ~ 종료일 간 주말을 제외한 영업일 계산
 * @param startDate
 * @param endDate
 * @returns number
 */
function calculateBusinessDay(startDate, endDate) {
	startDate = new Date(startDate);
	endDate = new Date(endDate);

	let count = 0;
	let flag = true;
	const tmpDate = startDate;

	while (flag) {
		if (tmpDate.getTime() > endDate.getTime()) {
			flag = false;
		} else {
			const tmpDay = tmpDate.getDay();
			if (tmpDay == 0 || tmpDay == 6) {
			} else {
				count++;
			}
			tmpDate.setDate(startDate.getDate() + 1);
		}
	}

	return count;
}