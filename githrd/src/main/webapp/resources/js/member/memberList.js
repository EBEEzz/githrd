$(document).ready(function() {
	$('#hbtn').click(function() {
		$(location).attr('href', '/www/');
	});
	
	$('.lbtn').click(function() {
		// 누구 버튼이 클릭이 되었는지 알아내고
		var sno = $(this).attr('id');
		// 입력태그에 데이터 심고
		$('#mno').val(sno);
		// 폼 전송하고
		$('#frm').submit();
	});
});