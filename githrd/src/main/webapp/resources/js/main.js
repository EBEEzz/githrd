$(document).ready(function() {
	$('#lbtn').click(function() {
		$(location).attr('href', '/www/member/login.blp');
	});
	$('#obtn').click(function() {
		$(location).attr('href', '/www/member/logout.blp');
	});
	$('#jbtn').click(function() {
		$(location).attr('href', '/www/member/join.blp');
	});
});