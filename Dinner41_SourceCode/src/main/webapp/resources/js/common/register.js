$(document).ready(function(){
	var contextPath=sessionStorage.getItem('contextPath');
	var wrap=$('#wrap');
	var geocoder = new kakao.maps.services.Geocoder();
	var contextPath=sessionStorage.getItem("contextPath");

	

	
	$('#user_email_button').on("click",function(){
		let email=$('#user_email').val();
		console.log(email);
		console.log(contextPath+'/register/checkEmail');
		$.ajax({
			url:contextPath+'/register/checkEmail',
			method:'post',
			data:{
				'email':email
			},
			success:function(data){
				console.log('아작스 성공');
				if(data.result==true){
					Swal.fire({
						title:'이미 등록된 이메일입니다',
						text:'다른 이메일로 등록해주세요',
						icon:'error'
					});
					$('#user_email').val(data.user.email);
				}
				else{
					Swal.fire({
						title:'등록이 가능한 이메일입니다.',
						text:'회원가입을 계속 진행해주세요',
						icon:'success'
					});					
				}
			},
			error:function(){
				console.log('아작스 실패');
			}
		});	
	});
	
	$('#searchButton').on("click",function(){
		//alert('주소검색버튼 클릭 이벤트 발생');
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
		    	 var longitude=document.getElementById('user_longitude');
		    	 longitude.value=lng;
		    	 //alert(latitude.value);
		    	 //alert(longitude.value);
		    	 Swal.fire({
		    		  title: '정말로 가입하시겠습니까?',
		    		  text: "dinner41의 회원이 되어주세요",
		    		  icon: 'question',
		    		  showCancelButton: true,
		    		  confirmButtonColor: '#3085d6',
		    		  cancelButtonColor: '#d33',
		    		  confirmButtonText: '네',
		    		cancelButtontext: '아니오'
		    		}).then((result) => {
		    		  if (result.value) {
		    		    Swal.fire(
		    		      '환영합니다',
		    		      'dinner41의 회원이 되셨습니다',
		    		      'success'
		    		    )
		    		    $('#register_form').submit();
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
		
		
	})
});
