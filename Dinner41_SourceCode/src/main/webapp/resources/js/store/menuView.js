$(document).ready(function() {
	var contextPath=sessionStorage.getItem("contextPath");
	var storeId = $('#storeId').val();
	var menuId =$('#menuId').val();
	
	
	$('#menuDelete').click(function() {
		
		Swal.fire({
			  title: '정말 삭제하시겠습니까?',
			  text: "메뉴를 삭제하면 복구할 수 없습니다",
			  icon: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '네',
			  cancelButtonText: '아니오'
			}).then((result) => {
			  if (result.value) {
				  
				  setTimeout(function() {
					  Swal.fire(
						      '삭제성공',
						      '',
						      'success'
						    )
				    			}, 1000);
			    
			   // var storeId = $('#storeId').val();
				//var menuId =$('#menuId').val();
				
				$.ajax({
					url :contextPath+"/menu/delete",
					method: "GET",
					data : {
						"storeId": storeId,
				        "menuId" : menuId
					},
					success : function(data) {
						
						if(data.result){
						window.location.href=contextPath+"/sm/1/menu/list";
						}
						else{
							window.location.href=contextPath+"/sm/1/menu/list";
						}
					},
				
				});
			  }
			});
	});

})
