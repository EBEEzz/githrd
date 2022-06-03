$(document).ready(function() {
	$('#hbtn').click(function() {
		$(location).attr('href', '/www/');
	});
	
	$('.lbtn').click(function() {
		let id = $(this).attr('id');
		$('#id').val(id);
		$('#frm').submit();
	});
});