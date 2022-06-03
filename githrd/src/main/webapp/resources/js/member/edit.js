$(document).ready(function() {
	
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	$('#hbtn').click(function(){
		$(location).attr('href', '/www/');
	});
	
});