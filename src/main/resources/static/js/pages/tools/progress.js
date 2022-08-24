$(function() {

});

function calculateDate() {
    const startDate = new Date(2022, 7, 25);
    const endDate = new Date(2022, 9, 4);

    let count = 0;
    let flag = true;

    while (flag) {
        let tmp_date = startDate;
        if (tmp_date.getTime() > endDate.getTime()) {
            console.log('count : ' + count);
            flag = false;
        } else {
            const tmp = tmp_date.getDay();
            if (tmp == 0 || tmp == 6) {
                console.log('red');
            } else {
                console.log('black');
            }
            tmp_date.setDate(startDate.getDate() + 1);
        }
    }
}