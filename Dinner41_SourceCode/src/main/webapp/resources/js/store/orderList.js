$(document).ready(function() {
	clickReceiptComple();
	clickStoreDetail();
	disabledReceiptClick();
});

// 수령완료버튼을 사용하지 못하도록
function disabledReceiptClick() {
	var pickupDate = $("#pickupDate").data("pickupdate");
	if (pickupDate) {
		var orders = $(".orderClass").get();
		for (let i = 0; i < orders.length; i++) {
			var order = $(orders[i]);
			order.find("#receiptComplete").attr("disabled", true);
			order.find("#cancelOrder").attr("disabled", true);
		}
	}
}

// 수령 처리
function clickReceiptComple() {
	var orders = $(".orderClass").get();
	for (let i = 0; i < orders.length; i++) {
		var order = $(orders[i]);
		order.find("#receiptComplete").click(function() {
			var orderId = $(this).parents(".orderClass").data("orderid");

			$.ajax({
				url : getContextPath() + "/sm/order/complete",
				method : "GET",
				data : {
					"orderId" : orderId
				},
				success : function(data) {
					if (data.result) {
						swal.fire(data.msg);
						window.location = getContextPath() + "/sm/WAIT/1/order"
					}
				}
			});
		});
	}
}

// 주문 내역에서 매장 상세보기 버튼 클릭
function clickStoreDetail() {
	var orders = $(".orderClass").get();
	for (let i = 0; i < orders.length; i++) {
		var order = $(orders[i]);
		order.find("#storeDetail").click(
				function() {
					var orderId = $(this).parents(".orderClass")
							.data("orderid");
					window.location = getContextPath() + "/sm/" + orderId
							+ "/order/detail";
				});
	}
}

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
}
