$(document).ready(function(){
	$('#selList, #eList, #infoBox').css('display', 'none');

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
				$('#selList, #eList').css('display', 'none').html('<option class="w3-center" disabled selected># 선 택 #</option>');
				if(txt == 'dept'){
					for(var i = 0 ; i < arr.length ; i++ ){
						var str = '<option class="w3-center" value=' + arr[i].dno + '>' + arr[i].dname + '</option>';
						$('#selList').append(str);
					}
				} else {
					for(var i = 0 ; i < arr.length ; i++ ){
						var str = '<option class="w3-center" value=' + arr[i].job + '>' + arr[i].job + '</option>';
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
				$('#eList').html('<option class="w3-center" disabled selected># 사원 선택 #</option>');

				for(var i = 0; i < arr.length ; i++ ){
					$('#eList').append('<option class="w3-center" value=' + arr[i].eno + '>' + arr[i].name + '</option>');
				}

				$('#eList').css('display', 'block');
			},
			error: function(){
				alert('### 준영씨에게 문의하세요! ###');
			}
		});
	});
});