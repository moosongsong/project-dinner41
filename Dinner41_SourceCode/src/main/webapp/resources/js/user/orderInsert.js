$(document).ready(function() {
	clickOrder();
});

function clickOrder() {
	$("#order").click(function() {
		var getTime = $("#get_time").val();
		var payTotal = $("#pay_total").val();

		if (getTime.trim() == "") {
			swal.fire("수령 소요 시간을 입력해주세요.");
			$("#get_time").focus();
			return;
		}

		$.ajax({
			type : "POST",
			url : "order",
			data : {
				"getTime" : getTime,
				"payTotal" : payTotal
			},
			success : function(data) {
				var user = data.user;
				var storePayNumber = data.storePayNumber;
				var orderId = data.orderId;
				var price = data.price;
				var menuIds = data.menuIds;
				var storeId = data.storeId;

				sessionStorage.setItem("user", user);
				sessionStorage.setItem("storePayNumber", storePayNumber);
				sessionStorage.setItem("orderId", orderId);
				sessionStorage.setItem("price", price);
				sessionStorage.setItem("menuIds", menuIds);
				sessionStorage.setItem("storeId", storeId);

				swal.fire({
					title : "결제 페이지로 이동합니다."
				}).then((result) => {
					if (result.value) {
						// 결제 페이지로 이동
						window.location = getContextPath() + "/gm/pay";
					}
				});
			}
		});
	});
}

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
}
