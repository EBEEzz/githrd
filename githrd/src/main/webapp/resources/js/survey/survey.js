$(document).ready(function(){
	// .yet 클릭이벤트
	$('.yet').click(function(){
		var sno = $(this).attr('id');
		var txt = $(this).html();
		txt = txt.substring(txt.indexOf('.') + 2);
		
		$('#title').val(txt);
		$('#sino').val(sno);
		
//		alert($('#title').val() + ' - ' + $('#sino').val());
		
		$('#frm').submit();
	});
});