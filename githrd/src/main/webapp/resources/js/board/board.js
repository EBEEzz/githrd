$(document).ready(function(){
	$('#hbtn').click(function(){
		$(location).attr('href', '/www/main.blp');
	});
	
	$('#obtn').click(function(){
		$(location).attr('href', '/www/member/logout.blp');
	});
	
	$('#lbtn').click(function(){
		$(location).attr('href', '/www/member/login.blp');
	});
	
	$('#jbtn').click(function(){
		$(location).attr('href', '/www/member/join.blp');
	});
	
	$('.pbtn').click(function(){
		// 클릭된 버튼의 아이디 읽고
		var spage = $(this).attr('id');
		
		$('#nowPage').val(spage);
		$('#bno').prop('disabled', true);
		
		// action 속성 변경하고
		$('#frm').attr('action', '/www/board/boardList.blp');
		$('#frm').submit();
		
	});
	
	$('.brdList').click(function(){
		var sno = $(this).attr('id');
		$('#bno').val(sno);
		$('#frm').submit();
	});
});