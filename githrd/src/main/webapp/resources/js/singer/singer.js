$(function(){
	$('#photoBox, #gselect, #mselect, #photoBox').css('display', 'none');

	$('#stype').change(function(){
		$('#gselect, #mselect').css('display', 'none');

		var gn = $('#gn').val();
		var gen = $('#gen').val();
		var stype = $(this).val();
		if(!gn) return;
		if(!gen) return;

		$('#gselect').html('<option disabled selected>::: 가수 선택 :::</option>');

		$.ajax({
			url: '/www/singer/singerList.blp',
			dataType: 'json',
			data: {
				a_time: gn,
				s_gen: gen,
				scode: stype
			},
			success: function(data){
				for(var i=0; i < data.length ; i++ ){
					if(data[i].scode == 'G'){
						$('#gselect').append('<option class="G" value="' + data[i].num + '">' + data[i].name + '</option>');
					} else {
						$('#gselect').append('<option class="S" value="' + data[i].num + '">' + data[i].name + '</option>');
					}
				}

				$('#gselect').css('display', 'block');
			},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
	});

	// 가수 선택 이벤트 처리
	$('#gselect').change(function(){
		$('#photoBox, #mselect, #photoBox').css('display', 'none');
		var sno = $(this).val();
		var str = $('#gselect > option:selected').attr('class');
		$('#mselect').append('<option disabled selected>::: 멤버 선택 :::</option>');

		var path = '/www/singer/memberList.blp';

		if(str == 'S'){
			path = '/www/singer/getPhoto.blp';
		}

		$.ajax({
			url: path,
			type: 'post',
			dataType: 'json',
			data: {
				num: sno,
				scode: str
			},
			success: function(data){
				if(str == 'S'){
					// 솔로 사진 가져오기 처리
					$('.infoAvtBox').attr('src', '/www/img/photo/' + data.sname);
					$('#photoBox').css('display', 'block');
				} else {
					// 멤버 리스트 조회 처리
					for(var i = 0 ; i < data.length ; i++ ){
						$('#mselect').append('<option value="' + data[i].num + '">' + data[i].name + '</option>');
					}
					$('#mselect').css('display', 'block');
				}
			},
			error: function(){
				alert('### 통신 오류 ###');
			}
		});
	});


	$('#mselect').change(function(){
		$('#photoBox').css('display', 'none');
		var sno = $(this).val();

		$.ajax({
			url: '/www/singer/getPhoto.blp',
			type: 'post',
			dataType: 'json',
			data: {
				num: sno
			},
			success: function(data){
				$('.infoAvtBox').attr('src', '/www/img/photo/' + data.sname);
				$('#photoBox').css('display', 'block');
			},
			error: function(){
				alert('### 통신 오류 ###');
			}
		});
	});
});