$(document).ready(function(){
	$('#msgClose').click(function(){
		$('#msgWin').css('display', 'none');
		
		$.ajax({
			url: '/www/mainMsgCheck.blp',
			type: 'post',
			dataType: 'json',
			success: function(data){
				if(data.result == 'OK'){
					// 처리에 성공한 경우
					$('#msgWin').remove();
				}
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
	});
	
	$('#lbtn').click(function(){
		$(location).attr('href', '/www/member/login.blp');
	});
	$('#obtn').click(function(){
		$(location).attr('href', '/www/member/logout.blp');
	});
	
	$('#jbtn').click(function(){
		$(location).attr('href', '/www/member/join.blp');
	});
	
	/* 내 정보보기 버튼 클릭이벤트 */
	$('#ibtn').click(function(){
		$('#frm').attr('action', '/www/member/myInfo.blp');
		$('#frm').submit();
	});
	
	/* 회원 목록 버튼 클릭 이벤트 */
	$('#mlbtn').click(function(){
		$(location).attr('href', '/www/member/memberList.blp');
	});
	
	/* 방명록 버튼 클릭 이벤트  */
	$('#gbtn').click(function(){
		$(location).attr('href', '/www/gBoard/gBoardList.blp');
	});
	
	/* 댓글게시판 버튼 클릭이벤트 */
	$('#rbtn').click(function(){
		$(location).attr('href', '/www/reBoard/reBoardList.blp');
	});
	
	/* 파일게시판 클릭이벤트 */
	/*
	$('#fbtn').click(function(){
		$(location).attr('href', '/www/fileBoard/fileBoardList.blp');
	});
	*/
	
	$('#sbtn').click(function(){
		$('#frm').attr('action', '/www/survey/surveyInfo.blp');
		$('#frm').submit();
	});
});