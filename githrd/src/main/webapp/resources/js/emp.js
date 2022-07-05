$(document).ready(function(){
	$('#selList, #eList').css('display', 'none');

	$('#sel').change(function(){
		var txt = $(this).val();

		$.ajax({
			url: '/www/emp/selList.blp',
			type: 'post',
			dataType: 'json',
			data: {
				sel: txt
			},
			success: function(arr){
				$('#selList').html('<option disabled selected># 분류선택 #</option>');
				if(txt == 'dept'){
					for(var i = 0 ; i < arr.length ; i++ ){
						var str = '<option value=' + arr[i].dno + '>' + arr[i].dname + '</option>';
						$('#selList').append(str);
					}
				} else {
					for(var i = 0 ; i < arr.length ; i++ ){
						var str = '<option value=' + arr[i].job + '>' + arr[i].job + '</option>';
						$('#selList').append(str);
					}
				}

				// selList 태그 보이게...
				$('#selList').css('display', 'block');
			},
			error: function(){
				alert('### 준영씨에게 문의하세요! ###');
			}
		});
	});

	$('#selList').change(function(){
		// 할일
		// 1. 선택한 내용 알아내고
		var txt = $(this).val();
		var prevSel = $(this).prev().val();
		var sendData = {};

		if(prevSel == 'job'){
			sendData = {
				job: txt	
			};
		} else {
			sendData = {
				dno: txt	
			};
		}

		$.ajax({
			url: '/www/emp/partList.blp',
			type: 'post',
			dataType: 'json',
			data: sendData,
			success: function(arr){
				$('#eList').html('<option disabled selected># 조회분류선택 #</option>');

				for(var i = 0; i < arr.length ; i++ ){
					$('#eList').append('<option value=' + arr[i].eno + '>' + arr[i].name + '</option>');
				}

				$('#eList').css('display', 'block');
			},
			error: function(){
				alert('### 준영씨에게 문의하세요! ###');
			}
		});
	});
});