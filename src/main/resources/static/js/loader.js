function loader(mode) {
	switch (mode) {
		case 0:
			$('#loader').addClass('hidden');
			break;
		case 1:
			$('#loader').removeClass('hidden');
			break;
	}
}
