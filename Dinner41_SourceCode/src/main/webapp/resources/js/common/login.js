$(document).ready(function(){
	const contextPath=sessionStorage.getItem("contextPath");
	const resultEmail=$('#modelData').data('resultemail');
	//console.log(resultEmail+" 1");
	const errorMessage=$('#modelData').data('errormessage');
	//console.log(errorMessage+" 2");
	if(resultEmail){
		//alert(resultEmail+"님에게 임시 비밀번호를 발급했습니다.이메일을 확인해주세요");
		Swal.fire(resultEmail+"님에게 임시 비밀번호를 발급했습니다.이메일을 확인해주세요");
		$('#user_email').val(resultEmail);
		$('#user_password').focus();
	}
	//console.log('에러메세지 없음');
	//console.log(contextPath);
	if(errorMessage){
		//alert(errorMessage+"");
		Swal.fire(errorMessage+"");
	}
	$('#register_button').on("click",function(){
		//alert("회원가입버튼에 클릭이벤트 발생");
		location.href=contextPath+"/register";
	});
	$('#password_button').on("click",function(){
		//alert("passwordButton의 onClick이벤트 발생");
		location.href=contextPath+"/password";
	});

});