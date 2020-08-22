$(document).ready(function(){
	var contextPath=sessionStorage.getItem("contextPath");
	alert(contextPath+"");
	$('#update_button').on("click",function(){
		var password=prompt("비밀번호를 입력해주세요","");
		alert(password);
		//$('#password').val(password);
		//$('#update_form').attr("method","patch");
		//$('#update_form').submit();
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
	});
	$('#delete_button').on("click",function(){
		var password=prompt("비밀번호를 입력해주세요","");
		alert(password);
		$('#password').val(password);
		$.ajax({
			url:contextPath+"/mypage/checkPass",
			data:{
				"password":password
			},
			method:"post",
			success:function(data){
				if(data.result){
					alert('아작스 성공');
					window.location.href=contextPath+"/mypage/delete";

				}
				else{
					window.location.href=contextPath+"/mypage";
				}
			}
		});
	});
	
});