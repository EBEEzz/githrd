$(document).ready(function() {
	$('#lbtn').click(function() {
		$('#frm').attr('action', '/www/member/loginProc.blp');
		$('#frm').submit();
	});
	
	$('#hbtn').click(function() {
		$(location).attr('href', '/www/');
	});
});