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
		$(location).attr('href', '/www/member/login.blp?vw=/www/reBoard/reBoardList.blp&nowPage=' + $('#nowPage').val());
	});
	
	$('#jbtn').click(function(){
		$(document.frm).attr('action', '/www/member/join.blp');
		$('#frm').submit();
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
	
	$('.comment').click(function() {
		var sno = $(this).parent().attr('id');
		$('#bno').val(sno);
		
		$('#frm').attr('action', '/www/reBoard/commentWrite.blp');
		$('#frm').submit();
	});
	
	$('#cmtbtn').click(function() {
		var txt = $('#body').val();
		if(!txt) {
			return;
		}
		
		$('#frm').submit();
	});
	
	$('.editbtn').click(function() {
		var sno = $(this).parent().attr('id');
		$('#bno').val(sno);
		
		$('#frm').attr('action', '/www/reBoard/reBoardEdit.blp');
		$('#frm').submit();
	});
	
	$('#editbtn').click(function() {
		//태그 내용 읽기
		var oritxt = $('#obody').val();
		var txt = $('#body').val();
		
		if(oritxt == txt) {
			alert('### 수정된 내용이 없습니다. ###');
			return;
		}
		
		$('#frm').submit();
	});
	
	$('.delbtn').click(function() {
		var sno = $(this).parent().attr('id');
		$('#bno').val(sno);
		
		$('#frm').attr('action', '/www/reBoard/delReBoard.blp');
		$('#frm').submit();
	});
});