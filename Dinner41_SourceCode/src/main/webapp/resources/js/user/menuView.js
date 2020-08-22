$(document).ready(function() {
	insertCart();
});

function insertCart() {
	var storeId = $(".container-fluid").data("storeid");
	var menuId = $(".container-fluid").data("menuid");

	// 장바구니에 메뉴 등록
	$("#insertCart").click(
			function() {
				$
						.ajax({
							type : "POST",
							url : getContextPath() + "/gm/cart",
							data : {
								"storeId" : storeId,
								"menuId" : menuId
							},
							success : function(data) {
								if (data.result) {
									if (data.msg1 != undefined) {
										swal.fire({
											title : data.msg1,
										}).then((result) => {
											if (result.value) {
												window.location.href = getContextPath()
												+ "/" + storeId + "/" + menuId
												+ "/menu/view";
											}
										});
										}
									
									swal.fire({
										title : data.msg2,
									}).then((result) => {
										if (result.value) {
										window.location.href = getContextPath()
												+ "/" + storeId + "/" + menuId
												+ "/menu/view";
										}
									});
									
								} else {
									swal.fire(data.msg);
									// 로그인 화면으로 이동하는 URL
									winow.location.href = "/dinner41";
								}
							}
						});
			});

	$("#cartButton").click(function() {
		window.location.href = "gm/cart";
	});
}

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
}