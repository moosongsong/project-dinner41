$(document).ready(function(){
	let latitude=$('#map').data('latitude');
	let longitude=$('#map').data('longitude');
	let mapContainer = document.getElementById('map'); // 지도를 표시할 div 
	let mapOption = { 
		center: new kakao.maps.LatLng(latitude,longitude),
		level: 3 // 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	var markerPosition  = new kakao.maps.LatLng(latitude,longitude);
	var marker = new kakao.maps.Marker({
		position: markerPosition
	});
	marker.setMap(map);	
});