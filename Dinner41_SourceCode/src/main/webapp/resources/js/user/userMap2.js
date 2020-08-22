
var isRun=false;
var geocoder = new kakao.maps.services.Geocoder();
const contextPath=sessionStorage.getItem("contextPath");
var markers=[];

function removeMarkers(markers){
	console.log("removeMarker진입");
	if(markers.length!=0){
		for(let i=0;i<markers.length;i++){
			console.log(markers[i]);
			markers[i].setMap(null);
		}
		markers.length=0;
	}
}

function getStoreMarkers(map,latitude,longitude,keyword){
	const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	const imageSize=new kakao.maps.Size(24,35);
	let markerImage=new kakao.maps.MarkerImage(imageSrc,imageSize);

	console.log("getStoreMarkers함수 진입");
	console.log("getStoreMarkers: isRun="+isRun);
	console.log("getStore:"+latitude);
	console.log("getStore:"+longitude);
	console.log("getStore:"+keyword);
	
	if(isRun==true){
		return;
	}
	isRun=true;

	$.ajax({
		url:contextPath+'/gm/map',
		method:'post',
		data:{
			'latitude':latitude,
			'longitude':longitude,
			'keyword':keyword
		},
		success:function(data){
			if(data.result==true){
				if(data.storeList){
					console.log("getStore:"+"ajax성공");
					//markers=data.storeList;
					console.log("getStore:"+markers);
					console.log("getStore:"+latitude);
					console.log("getStore:"+longitude);
					for(let i = 0; i < data.storeList.length; i++){
						let store = data.storeList[i];
						console.log("getStore:"+store.name);
						let marker=new kakao.maps.Marker({
							position:new kakao.maps.LatLng(store.latitude,store.longitude),
							image:markerImage,
						});
						let iwContent = "<div style='background-color:#CFE3A1;padding:5px;'><a href='"+contextPath+"/gm/"+store.id+"/menu/store' style='color:#6F923E'>"+store.name+"</a><br/><a href='tel:"+store.phone+"'>"+store.phone+"</a></div>", // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					    	iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
						let infowindow = new kakao.maps.InfoWindow({
							content : iwContent,
							removable : iwRemoveable
						});
						kakao.maps.event.addListener(marker, 'click', function() {
							infowindow.open(map, marker);  
						});
						marker.setMap(map);
						markers[markers.length]=marker;
						/*
						let marker=new kakao.maps.Marker({
							map:map,
							position:new kakao.maps.LatLng(store.latitude,store.longitude),
							title:store.name,
							image:markerImage
						});
						*/
					}
					console.log(markers);
				}
				else{//제대로 검색했는데 결과가 0 인경우
					swal.fire('사용자 주변 1km내에 제휴 매장이  존재하지 않습니다.');
					removeMarkers(markers);
				}
			}
			else{//exception 터졌을 경우
				swal.fire('지도를 불러오는 데 실패했습니다.');
				removeMarkers(markers);
			}
			isRun=false;
		},
		error:function(){
			swal.fire('지도를 불러오는 데 실패했습니다.');
			isRun=false;
			for(let marker in markers){
				marker.setMap(null);
			}
		}
	});
}


function addressToCoor(address,map,callback){
	console.log("addressToCoor진입");
	console.log("addressToCoor:"+address);
	geocoder.addressSearch(address, function(result, status) {
		 if (status === kakao.maps.services.Status.OK) { //정상 검색
			 let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			 let latitude=coords.getLat();

			 sessionStorage.setItem("currentLatitude",latitude);
			 console.log("addressToCoor:"+sessionStorage.getItem('currentLatitude'));

			 let longitude=coords.getLng();

			 sessionStorage.setItem("currentLongitude",longitude);
			 console.log("addressToCoor:"+sessionStorage.getItem('currentLongitude'));
			 
			 let keyword=sessionStorage.getItem('selectedCategory');
			 console.log("addressToCoor:"+sessionStorage.getItem('selectedCategory'));

			 moveTo(map,latitude,longitude);
			 return callback(map,latitude,longitude,keyword);
		} 
	});    
}

function getAddress(searchArea,map,callback,callback2){
	console.log("getAddress잔압");
	//console.log("getAddress:"+searchArea);
	//console.log("getAddress:"+map);
	//console.log("getAddress:"+callback);
	//console.log("getAddress:"+callback2);

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
			console.log(address);
			return callback(address,map,callback2);
		},
		onclose:function(state){
			$(searchArea).val(address);
		}
	}).open({
		autoClose:'true'
	});
}

function moveTo(map,latitude,longitude) {
	console.log("moveTo진입");
    var moveLatLon = new kakao.maps.LatLng(latitude,longitude);
	const marker=new kakao.maps.Marker({
		position:new kakao.maps.LatLng(latitude,longitude),
	});
	marker.setMap(map);
    map.panTo(moveLatLon);            
}       


$(document).ready(function(){
	console.log('js문서 진입');
	console.log("기본 유저의 위도:"+sessionStorage.getItem('currentLatitude'));
	console.log("기본 유저의 경도:"+sessionStorage.getItem('currentLongitude'));
	console.log("마커 배열:"+markers);
	
	let latitude=sessionStorage.getItem('currentLatitude');
	let longitude=sessionStorage.getItem('currentLongitude');

	const mapContainer=document.getElementById('map');
	
	const mapOption = { 
	center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
		level: 3 // 지도의 확대 레벨
	};
	const map = new kakao.maps.Map(mapContainer, mapOption);
	const marker=new kakao.maps.Marker({
		map:map,
		position:new kakao.maps.LatLng(latitude,longitude),
	});
	
	getStoreMarkers(map,latitude,longitude,sessionStorage.getItem('selectedCategory'));
	
	$('#search').on("click",function(){
		getAddress($('#search'),map,addressToCoor,getStoreMarkers);
	});
	
	$('#button_area').children().on("click",function(event){
		console.log('버튼 클릭 이벤트 발생');
		console.log(event.target);
		let selectedButton=event.target.value;
		console.log(selectedButton);
		sessionStorage.setItem("selectedCategory",selectedButton);
		getStoreMarkers(map,sessionStorage.getItem('currentLatitude'),sessionStorage.getItem('currentLongitude'),selectedButton);
	});
});