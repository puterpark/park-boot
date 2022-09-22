/* 메뉴 이동 */
function goMenu(url) {
	window.location.href = url;
}

$(function() {
	$(document).on('click', '#icon-gear', function(e){
		e.preventDefault();
		e.stopPropagation();
		goMenu('/admin');
	});
});
