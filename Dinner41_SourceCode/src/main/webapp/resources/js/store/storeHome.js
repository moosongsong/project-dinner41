$(document).ready(function(){
	let contextPath=sessionStorage.getItem("contextPath");
	
	$('#openBtn').click(function(){
		 let name =$('#openBtn').attr('name');
		 Swal.fire('매장을 오픈하였습니다');
		 setTimeout(function() {
			 location.href = contextPath+"/sm/switchOpenState/"+name+"/store";
		    			}, 1000);
	});
	
	$('#closeBtn').click(function(){
		let name =$('#closeBtn').attr('name');
		 Swal.fire('매장을 마감하였습니다');
		 setTimeout(function() {
			 location.href = contextPath+"/sm/switchOpenState/"+name+"/store";
		    			}, 1000);
	});
});