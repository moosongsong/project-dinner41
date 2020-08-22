$(document).ready(function(){
	var wrap=$('#wrap');
	var geocoder = new kakao.maps.services.Geocoder();
	$('#searchButton').on("click",function(){
		alert('주소검색버튼 클릭 이벤트 발생');
		var address='';
        new daum.Postcode({
            oncomplete: function(data) {
            	address=data.roadAddress;
            },
            onclose:function(state){
				$('#wrap').css('display','none');
				$('#user_address').val(address);
				$('#user_detailAddress').focus();
            }
        }).embed(
        		document.getElementById('wrap'),
        		{autoClose:'true'}
        );
        $('#wrap').css('display','block');
	});
	$('#register_apply_button').on("click",function(){
		var address=$('#user_address').val();
		geocoder.addressSearch(address, function(result, status) {
		     if (status === kakao.maps.services.Status.OK) { //정상 검색

		    	 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		    	 var lat=coords.getLat();
		    	 var lng=coords.getLng();
		    	 //alert(lat);
		    	 //alert(lng);
		    	 //alert("latitude:"+lat+",longitude:"+lng);
		    	 var latitude=document.getElementById('user_latitude');
		    	 latitude.value=lat;
		    	 //alert($('#user_latitude').val());
		    	 var longitude=document.getElementById('user_longitude');
		    	 longitude.value=lng;
		    	// $('#user_longitude').val(lng);
		    	 //alert($('#user_longitude').val());
		    	 //alert(latitude.value);
		    	 //alert(longitude.value);
		    	 Swal.fire({
		    		  title: '정말로 수정하시겠습니까?',
		    		  text: "'네'를 누를시 매장정보가 수정됩니다",
		    		  icon: 'question',
		    		  showCancelButton: true,
		    		  confirmButtonColor: '#3085d6',
		    		  cancelButtonColor: '#d33',
		    		  confirmButtonText: '네',
		    		  cancelButtonText: '아니오'
		    		}).then((result) => {
		    		  if (result.value) {
		    		    Swal.fire(
		    		      '수정완료',
		    		      '매장정보가 수정되었습니다.',
		    		      'success'
		    		    );
		    		    setTimeout(function() {
		    		    	 $('#register_form').submit();
					    			}, 1000);
		    		  }
		    		});
		    	 
		    	 /*
		    	 $('#user_latitude').val(""+latitude);
		    	 const la=$('#user_latitude');
		    	 alert(""+la);
		    	 $('#user_longitude').val(""+longitude);
		    	 */
		    } 
		});    
	});
	$('#register_cancel_button').on("click",function(){
		Swal.fire({
  		  title: '수정을 취소하시겠습니까?',
  		  text: "'네'를 누르면  홈화면으로 이동합니다",
  		  icon: 'question',
  		  showCancelButton: true,
  		  confirmButtonColor: '#3085d6',
  		  cancelButtonColor: '#d33',
  		  confirmButtonText: '네',
  		  cancelButtonText: '아니오'
  		}).then((result) => {
  		  if (result.value) {
  		    
  		  }
  		});
	});
	
	
});