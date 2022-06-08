$(document).ready(function() {
	$('.pbtn').click(function(){
		var spage = $(this).attr('id');
		$('#nowPage').val(spage);
		$('#frm').submit();
	});

	$('#hbtn').click(function(){
		$(location).attr('href', '/www/main.blp');
	});
	
	$('#lbtn').click(function(){
		$(location).attr('href', '/www/member/login.blp');
	});
	
	$('#jbtn').click(function(){
		$(location).attr('href', '/www/member/join.blp');
	});
	
	$('#obtn').click(function(){
		$('#frm').attr('action', '/www/member/logout.blp');
		$('#view').val('/www/reBoard/reBoardList.blp');
		$('#bno').prop('disabled', true);
		$('#frm').submit();
	});
	
	$('#modalClose').click(function(){
		$('#modal').css('display', 'none');
	});
	
	$('#wbtn').click(function(){
		$('#bno').prop('disabled', true);
		$('#view').prop('disabled', true);
		$('#frm').attr('action', '/www/reBoard/reBoardWrite.blp');
		$('#frm').submit();
	});
	
	/* 리셋버튼 클릭이벤트 */
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	/* 리스트 버튼 클릭이벤트 */
	$('#listbtn').click(function(){
		$('#frm').attr('action', '/www/reBoard/reBoardList.blp');
		$('#frm').submit();
	});
	
	/* 글 등록버튼 클릭 이벤트 */
	$('#wpbtn').click(function(){
		var txt = $('#body').val();
		if(!txt){
			return;
		}
		
		$('#frm').submit();
	});
});