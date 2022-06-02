$(document).ready(function(){
	$('#idck').click(function(){
		var sid = $('#id').val();
		
		if(!sid) return;
		
		$.ajax({
			url: '/www/member/idCheck.blp',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(data){
				var result = data.result;
				
				if(result == 'OK'){
					$('#idmsg').slideUp(300, function(){
						$('#idmsg').html('사용가능한 아이디 입니다.');
						$('#idmsg').removeClass('w3-text-blue w3-text-red').addClass('w3-text-blue').slideDown(300);
					});
				} else {
					$('#idmsg').slideUp(300, function(){
						$('#idmsg').html('사용 불가능한 아이디 입니다.');
						$('#idmsg').removeClass('w3-text-blue w3-text-red').addClass('w3-text-red').slideDown(300);
					});
				}
			},
			error: function(){
				alert('### 통신에러 ###');
			}
		});
	});
	
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	$('#hbtn').click(function(){
		$(location).attr('href', '/www/');
	});
	
	$('#gen').change(function(){
		var sgen = $('[name="gen"]:checked').val();
		
		$('#avtfr').stop().slideUp(300, function(){
			if(sgen == 'M'){
				$('#favt').css('display', 'none');
				$('#mavt').css('display', 'block');
			} else {
				$('#mavt').css('display', 'none');
				$('#favt').css('display', 'block');
			}
			
			$('#avtfr').stop().slideDown(300);
		});
	});
	
	$('#jbtn').click(function(){
		// 데이터 유효성 검사
		
		$('#frm').attr('action', '/www/member/joinProc.blp');
		$('#frm').submit();
	});
});