$(document).ready(function() {
	var contextPath = sessionStorage.getItem("contextPath");
	clickCountButton();
	clickCountUpdate(contextPath);
});

function clickCountButton() {
	var menus = $(".menuList").get();
	for (let i = 0; i < menus.length; i++) {
		var jMenus = $(menus[i]);
		var minusButton = jMenus.find("#minusButton");
		var plusButton = jMenus.find("#plusButton");

		minusButton.click(function() {
			var num = Number(($(this).siblings('#num')).text());
			--num;
			if (num <= 0) {
				num = 1;
			}
			// 버튼을 통해 메뉴의 수량을 바꿈
			($(this).siblings("#num")).text(num);

		});
		plusButton.click(function() {
			var num = Number(($(this).siblings('#num')).text());
			++num;
			// 버튼을 통해 메뉴의 수량을 바꿈
			($(this).siblings("#num")).text(num);
		});
	}
}

function clickCountUpdate(contextPath) {
	var menus = $(".menuList").get();
	for (let i = 0; i < menus.length; i++) {
		var menu = $(menus[i]);
		
		menu.find("#countUpdate").click(function() {
			var storeId = $(this).parents(".menuList").find("#data").data("storeid");
			var menuId = $(this).parents(".menuList").find("#data").data("menuid");
			var amount = $(this).parents(".menuList").find("#num").text();
			
			Swal.fire({
				  title: '해당 수량으로 변경할까요?',
				  text: "해당 수량이 0이면 회원들에게 노출되지 않습니다!",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: '네',
				  cancelButtonText: '아니오',
				  
				}).then((result) => {
				  if (result.value) {
				    Swal.fire(
				      '수량변경!',
				      '수량이 성공적으로 변경되었습니다.',
				      'success'
				    )
				    $.ajax({
						url : contextPath + "/sm/menu/list",
						method : "POST",
						data : {
							"storeId" : storeId,
							"menuId" : menuId,
							"amount" : amount
						},
						success : function(data) {
							if (data.result) {
								//alert(data.msg);
							} else {
								window.location.href = contextPath + "/sm/1/menu/list";
							}
						},
					});
				    
				  }
				})
		})
	}
	/*
	$('#countUpdate').click(function() {
		let input = confirm("해당 수량으로 변경하시겠습니까? ");
		if (input) {
			$.ajax({
				url : contextPath + "/sm/menu/list",
				method : "POST",
				data : {
					"storeId" : storeId,
					"menuId" : menuId,
					"num" : num
				},
				success : function(data) {
					if (data.result) {
						console.log(data);
						alert(data.msg);
					} else {
						window.location.href = contextPath + "/sm/1/menu/list";
					}
				},

			});
		}

	});
	*/
}
