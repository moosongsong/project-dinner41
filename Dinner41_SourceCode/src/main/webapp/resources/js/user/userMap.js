var isRun=false;
var geocoder = new kakao.maps.services.Geocoder();
const contextPath=sessionStorage.getItem("contextPath");
function loadStores(mapContainer,latitude,longitude,keyword){
	console.log("loadStores함수 진입");
	console.log(latitude);
	console.log(longitude);
	console.log(keyword);
	
	if(isRun==true){
		return;
	}

	isRun=true;

	const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	const imageSize=new kakao.maps.Size(24,35);

	$.ajax({
		url:contextPath+'/gm/map',
		method:'post',
		data:{
			'latitude':latitude,
			'longitude':longitude,
			'keyword':keyword
		},
		success:function(data){
			console.log("ajax성공");
			if(data.result==true){
				console.log("(ajax success)latitude:"+latitude);
				console.log("(ajax success)longitude:"+longitude);
				const mapOption = { 
					center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
					level: 3 // 지도의 확대 레벨
				};
				const map = new kakao.maps.Map(mapContainer, mapOption);
				const marker=new kakao.maps.Marker({
					map:map,
					position:new kakao.maps.LatLng(latitude,longitude),
					title:'현재',
				});
				for(let i = 0; i < data.storeList.length; i++){
					let store = data.storeList[i];
					console.log(store);
					console.log('아작스 success안의 for문 진입');
					let markerImage=new kakao.maps.MarkerImage(imageSrc,imageSize);
					let marker=new kakao.maps.Marker({
						map:map,
						position:new kakao.maps.LatLng(store.latitude,store.longitude),
						title:store.name,
						image:markerImage
					});
				}
			}
			else{
				swal.fire('지도를 불러오는 데 실패했습니다');
			}
			isRun=false;
		},
		error:function(){
			swal.fire('지도를 불러오는 데 실패했습니다.');
			isRun=false;
		}
	});
}

function addressToCoor(address){
	console.log(address);
	geocoder.addressSearch(address, function(result, status) {
		 if (status === kakao.maps.services.Status.OK) { //정상 검색
			 let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			 let latitude=coords.getLat();
			 console.log(latitude);
			 let longitude=coords.getLng();
			 console.log(longitude);
			 return {
				 'latitude':latitude,
				 'longitude':longitude
			 }
		} 
	});    
}

function postcode(area,targetArea){
	console.log(area);
	console.log(targetArea);

	let address='';
	const themeObj = {
	   bgColor: "#F6F9F0", //바탕 배경색
	   outlineColor: "#CFE3A1" //테두리
	};

	new daum.Postcode({
		width:screen.availWidth,
		height:screen.availHeight,
		theme:themeObj,
		oncomplete: function(data) {
			address=data.roadAddress;
		},
		onclose:function(state){
			$(area).css('display','none');
			$(targetArea).val(address);
		}
	}).open({
		autoClose:'true'
	});
	/*
	.embed(
		area,
		{autoClose:'true'}
	);
	*/
	$(area).css('display','block');
	return address;
}


$(document).ready(function(){
	let category=sessionStorage.getItem("selectedCategory");
	const latitude=$('#locationData').data('latitude');
	const longitude=$('#locationData').data('longitude');
	const mapContainer=document.getElementById('map');
	console.log(latitude);
	console.log(longitude);
	
	loadStores(mapContainer,latitude,longitude,'ALL');
	
	$('#search').on("click",function(){
		let address=postcode($('#wrap'),$('#search'));
		let latlng=addressToCoor(address);
		$('#locationData').data('latitude',latlng.latitude);
		$('#locationData').data('longitude',latlng.longitude);
		loadStores(mapContainer,latlng[0],latlng[1],category);
	});
	
	$('#button_area').children().on("click",function(){
		let selectedButton=$(this).val();
		console.log("버튼클릭됨"+selectedButton);
		sessionStorage.setItem("selectedCategory",selectedButton);
		loadStores(mapContainer,latitude,longitude,selectedButton);
	});
	
});