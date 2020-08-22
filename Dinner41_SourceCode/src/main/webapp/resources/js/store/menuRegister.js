$(document).ready(function(){
	let contextPath=sessionStorage.getItem("contextPath");
	$('#menuWrite').on("click",function(){
	 Swal.fire({
		  title: '정말로 등록하시겠습니까?',
		  text: "해당 정보로 메뉴가 등록됩니다",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '네',
		  cancelButtonText: '아니오'
		}).then((result) => {
		  if (result.value) {
		    Swal.fire(
		      '등록완료',
		      '메뉴가 등록되었습니다!',
		      'success'
		    ).then((result) => {
		    	$('#register_form').submit();
		    });
		  }
		});
	});

$('#menuWriteCancel').on("click",function(){
	 Swal.fire({
		  title: '등록을 취소하시겠습니까?',
		  text: "'네'를 누르면 홈화면으로 이동합니다.",
		  icon: 'question',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: '네',
		  cancelButtonText: '아니오'
		}).then((result) => {
		  if (result.value) {
			  setTimeout(function() {
			    	location.href=contextPath+"/sm/1/menu/list";
			    			}, 1000);
		  }
		});
}); 
});