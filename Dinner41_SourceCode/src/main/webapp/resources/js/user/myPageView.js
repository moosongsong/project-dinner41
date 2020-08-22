//var Swal = require('sweetalert2');

$(document).ready(function(){
	var contextPath=sessionStorage.getItem("contextPath");
	//alert(contextPath+"");
	$('#update_button').on("click",function(){
		//var password=prompt("비밀번호를 입력해주세요","");
		const { value: password } = setTimeout(function() {
			Swal.fire({
				  title: '비밀번호를 입력해주세요',
				  input: 'password',
				  inputPlaceholder: '비밀번호 입력',
				  inputAttributes: {
				    maxlength: 10,
				    autocapitalize: 'off',
				    autocorrect: 'off'
				  }
				});
			
		}); 
		
		setTimeout(function(){
			
			if (true) {
				$.ajax({
					url:contextPath+"/mypage/checkPass",
					method:"POST",
					data:{
						"password":1234
					},
					success:function(data){
						if(data.result){
								window.location.href=contextPath+"/mypage/update";
						}
						else{
							window.location.href=contextPath+"/mypage";
						}
					}
				});
			}
			
		},3000);
	
	
	
		//alert(password);
		//$('#password').val(password);
		//$('#update_form').attr("method","patch");
		//$('#update_form').submit();
		/*
		const { value: password } = await Swal.fire({
			  title: 'Enter your password',
			  input: 'password',
			  inputPlaceholder: 'Enter your password',
			  inputAttributes: {
			    maxlength: 10,
			    autocapitalize: 'off',
			    autocorrect: 'off'
			  }
		});
		if (password) {
			$.ajax({
				url:contextPath+"/mypage/checkPass",
				method:"POST",
				data:{
					"password":password
				},
				success:function(data){
					if(data.result){
						window.location.href=contextPath+"/mypage/update";
					}
					else{
						window.location.href=contextPath+"/mypage";
					}
				}
			});
		}
		*/
		
	});


	$('#delete_button').on("click",function(){
		const { value: password } = setTimeout(function() {
			Swal.fire({
				  title: '비밀번호를 입력해주세요',
				  input: 'password',
				  inputPlaceholder: '비밀번호 입력',
				  inputAttributes: {
				    maxlength: 10,
				    autocapitalize: 'off',
				    autocorrect: 'off'
				  }
				});
			
		}); 
		
		setTimeout(function(){
			
			if (true) {
				$.ajax({
					url:contextPath+"/mypage/checkPass",
					method:"POST",
					data:{
						"password":1234
					},
					success:function(data){
						if(data.result){
							Swal.fire('회원탈퇴가 완료되었습니다.');
							setTimeout(function(){
								window.location.href=contextPath+"/mypage/delete";
							},2000);
						}
						else{
							window.location.href=contextPath+"/mypage";
						}
					}
				});
			}
			
		},3000);
		
	
	});
	
});


/////////////////////////////////////////


//$(document).ready(function(){
//	var contextPath=sessionStorage.getItem("contextPath");
//	//alert(contextPath+"");
//	$('#update_button').on("click",function(){
//		var password=prompt("비밀번호를 입력해주세요","");
//		//alert(password);
//		//$('#password').val(password);
//		//$('#update_form').attr("method","patch");
//		//$('#update_form').submit();
//		/*
//		const { value: password } = await Swal.fire({
//			  title: 'Enter your password',
//			  input: 'password',
//			  inputPlaceholder: 'Enter your password',
//			  inputAttributes: {
//			    maxlength: 10,
//			    autocapitalize: 'off',
//			    autocorrect: 'off'
//			  }
//		});
//		if (password) {
//			$.ajax({
//				url:contextPath+"/mypage/checkPass",
//				method:"POST",
//				data:{
//					"password":password
//				},
//				success:function(data){
//					if(data.result){
//						window.location.href=contextPath+"/mypage/update";
//					}
//					else{
//						window.location.href=contextPath+"/mypage";
//					}
//				}
//			});
//		}
//		*/
//		$.ajax({
//			url:contextPath+"/mypage/checkPass",
//			method:"POST",
//			data:{
//				"password":password
//			},
//			success:function(data){
//				if(data.result){
//					window.location.href=contextPath+"/mypage/update";
//				}
//				else{
//					window.location.href=contextPath+"/mypage";
//				}
//			}
//		});
//	});
//
//
//	$('#delete_button').on("click",function(){
//		var password=prompt("비밀번호를 입력해주세요","");
//		//alert(password);
//		$('#password').val(password);
//		$.ajax({
//			url:contextPath+"/mypage/checkPass",
//			data:{
//				"password":password
//			},
//			method:"post",
//			success:function(data){
//				if(data.result){
//					//alert('아작스 성공');
//					window.location.href=contextPath+"/mypage/delete";
//
//				}
//				else{
//					window.location.href=contextPath+"/mypage";
//				}
//			}
//		});
//	});
//	
//});
















