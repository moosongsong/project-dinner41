$(document).ready(function(){
	var errorCode=$('#modelData').data("errorcode");
	var errorMessage=$('#modelData').data("errormessage");
	if (errorCode){
		//alert(errorMessage+"");
		Swal.fire(errorMessage+"");
		if(errorCode==="1"){
			console.log('err코드가 0 입니다');
			$('#user_email').focus();
		}
		else if(errorCode==="2"){
			let defaultEmail=$('#modelData').data("defaultemail");
			$('#user_email').val(defaultEmail);
			$('#password_button').focus();
		}
	}

	$('#password_button').on("click",function(){
		//alert('임시비밀번호 받기 버튼 클릭 이벤트 발생');
		let email=$('#user_email').val();
		//alert(email);
		if(!email.trim()){
			//alert("이메일을 입력해주세요");
			swal.fire("이메일을 입력해주세요");
			$('#user_email').focus();
		}
		else{
			//alert(email+"");
			
			Swal.fire({
	    		  title: '입력한 정보가 맞습니까?',
	    		  text: "'네'를 누르면 임시비밀번호가 이메일로 발급됩니다.",
	    		  icon: 'question',
	    		  showCancelButton: true,
	    		  confirmButtonColor: '#3085d6',
	    		  cancelButtonColor: '#d33',
	    		  confirmButtonText: '네',
	    		  cancelButtonText: '아니오'
	    			  
	    		}).then((result) => {
	    		  if (result.value) {
	    		    Swal.fire(
	    		      '임시비밀번호발급완료',
	    		      '',
	    		      'success'
	    		    )
	    		    $('#search_password_form').submit();
	    		  }
	    		});
			
		}
	});
});