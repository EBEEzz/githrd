$(document).ready(function(){

	$('#hbtn').click(function(){
		$(location).attr('href', '/www/main.blp');
	});
	
	$('#obtn').click(function(){
		$(location).attr('href', '/www/member/logout.blp');
	});
	
	$('#lbtn').click(function(){
		$('#vw').val('/www/board/boardList.blp');
		$('#frm').attr('action', '/www/member/login.blp');
		$('#frm').submit();
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
	
	$('#wbtn').click(function() {
		$('#frm').attr('action', '/www/board/boardWrite.blp');
		$('#frm').submit();
	});
	
	//리셋버튼 클릭이벤트
	$('#rbtn').click(function() {
		document.frm.reset();
	});
	
	$('#listbtn').click(function() {
		$('#frm').attr('action', '/www/board/boardList.blp');
		$('#frm').submit();
	});
	
	$('#filebox').on('change', '.upfile', function(evt){
		var str = $(this).val();
		var index = $(this).index();
		var tmp = $('.upfile');
		var max = tmp.length;
		if(!str){
			$(this).remove();
			$('.picbox').eq(index).remove();
			return;
		}
		var path = URL.createObjectURL(evt.target.files[0]);
		var el = $('.upfile');
		if((index + 1) != el.length){
			$('.infoAvtBox').eq(index).attr('src', path);
		}

		if(index == max - 1){
			$('#filebox').append('<input type="file" name="file" class="w3-input w3-border w3-margin-bottom upfile">');
			$('#preview').append('<div class="inblock pdAll10 picbox w3-card"><div class="w3-col w3-border" style="width: 100%; height: 100%; overflow: hidden;">' +
							'<img src="' + path + '" class="infoAvtBox">' + 
						'</div></div>');
		}
		$('#previewbox').css('display', 'block');
	});

	$('#wpbtn').click(function(){
		// 비어있는 input 태그 비활성 시켜놓고
		$('.upfile').last().prop('disabled', true);

		// 데이터 유효성 검사
		var title = $('#title').val();
		if(!title){
			$('#title').focus();
			return;
		}
		var body = $('#body').val();
		if(!body){
			$('#body').focus();
			return;
		}

		$('#frm').submit();
	});
	
});