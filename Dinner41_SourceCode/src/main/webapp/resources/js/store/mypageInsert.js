$(document).ready(function(){
	var geocoder = new kakao.maps.services.Geocoder();
	$('#search_button').on("click",function(){
		var address='';
        new daum.Postcode({
            oncomplete: function(data) {
            	address=data.roadAddress;
            },
            onclose:function(state){
				$('#wrap').css('display','none');
				$('#user_address').val(address);
				$('#user_sub_address').focus();
            }
        }).embed(
        		document.getElementById('wrap'),
        		{autoClose:'true'}
        );
        $('#wrap').css('display','block');
	});
	$('#goBackButton').on("click",function(){
		alert('뒤로가기버튼 클릭이벤트 발생');
		window.history.back();
	});
	
	$('#update_password_button').on("click",function(){
		alert("비밀번호 수정하기 버튼 클릭이벤트 발생");
		$('#InputNewPassword').css("display","block");
		$('#InputNewPasswordConfirm').css("display","block");
	});
	
	$('#complete_update_button').on("click",function(){
		alert('수정완료버튼 클릭 이벤트 발생');
		var user_address=$('#user_address').val();
		geocoder.addressSearch(user_address, function(result, status) {
		     if (status === kakao.maps.services.Status.OK) { //정상 검색
		    	 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		    	 var lat=coords.getLat();
		    	 var lng=coords.getLng();
		    	 alert(lat);
		    	 alert(lng);
		    	 var latitude=document.getElementById('user_latitude');
		    	 latitude.value=lat;
		    	 var longitude=document.getElementById('user_longitude');
		    	 longitude.value=lng;
		    	 $('#update_form').submit();
		    } 		
		});
		
	});
});