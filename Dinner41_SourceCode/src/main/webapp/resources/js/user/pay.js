$(document).ready(
		function() {
			var user = sessionStorage.getItem("user", user);
			var storePayNumber = sessionStorage.getItem("storePayNumber",
					storePayNumber);
			var orderId = sessionStorage.getItem("orderId", orderId);
			var price = sessionStorage.getItem("price", price);
			price = price.replace(/[^0-9]/g, '');
			var menuIds = sessionStorage.getItem("menuIds", menuIds);
			var storeId = sessionStorage.getItem("storeId", storeId);

			// 결제 API 함수 실행
			pay(storePayNumber, orderId, price, user.email, user.name,
					user.phone, user.addr, user.subAddr, menuIds, storeId);
		});

function pay(storePayNumber, orderId, price, email, name, phone, addr, subAddr,
		menuIds, storeId) {

	sessionStorage.setItem("payCompleteCheck", false);

	IMP.init(storePayNumber);
	IMP.request_pay({
		pg : 'inicis',
		pay_method : 'card',
		merchant_uid : 'merchant_' + new Date().getTime(),
		name : orderId,
		amount : price,
		buyer_email : email,
		buyer_name : name,
		buyer_tel : phone,
		buyer_addr : addr,
		buyer_postcode : subAddr
	/*
	 * , m_redirect_url : "http://192.168.30.29:8080/dinner41/gm/pay/result"
	 */
	}, function(rsp) {
		if (rsp.success) {
			var flag = true;
			var msg = '결제가 완료되었습니다.' + "\n";
			msg += '고유ID : ' + rsp.imp_uid + "\n";
			msg += '결제 금액 : ' + rsp.paid_amount + "\n";
			msg += '상점 거래ID : ' + rsp.merchant_uid + "\n";
			msg += '카드 승인번호 : ' + rsp.apply_num + "\n";
		} else {
			var flag = false;
			var msg = '결제에 실패하였습니다.' + '\n';
			msg += '에러내용 : ' + rsp.error_msg + "\n";
		}

		swal.fire({
			title : msg
		}).then((result) => {
			if (result.value) {
		if (flag) {
			var arr = [];
			for (let i = 0; i < menuIds.length; i++) {
				if (menuIds[i] == ',') {
					continue;
				}
				arr.push(String(menuIds[i]));
			}

			// 결제가 완료된 메뉴들의 수량을 하나씩 내림
			$.ajax({
				type : "PUT",
				url : getContextPath() + "/gm/" + storeId
						+ "/order/updateMenuAmount",
				data : {
					"arr" : arr
				},
				success : function(data) {

					// 결제가 완료된 메뉴들을 장바구니에서 삭제
					$.ajax({
						type : "DELETE",
						url : "cart",
						data : {
							"arr" : arr
						},
						success : function(data) {
							swal.fire({
								title : "결제가 완료된 메뉴들은 장바구니에서 삭제됩니다."
							}).then((result) => {
								if (result.value) {
									window.location.href = "WAIT/1/order";
								}
							});
						}
					});
				} 
			});
		}
		else {
			swal.fire({
				title : "결제를 실패했으므로 장바구니 화면으로 돌아갑니다."
			}).then((result) => {
				if (result.value) {
					window.location.href = "cart";
				}
			});
		}
			}
		});
			
		
	});
}

function getContextPath() {
	var hostIndex = location.href.indexOf(location.host) + location.host.length;
	return location.href.substring(hostIndex, location.href.indexOf('/',
			hostIndex + 1));
}
