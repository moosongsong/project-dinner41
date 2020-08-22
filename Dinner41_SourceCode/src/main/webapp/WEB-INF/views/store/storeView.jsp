<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<%
	String path = "../storeView/";
%>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script type="text/javascript" charset="UTF-8">
    	sessionStorage.setItem("contextPath","${pageContext.request.contextPath}");
    </script>
<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc023166bbc9c4e8ae23818cf48006fe&libraries=services,clusterer,drawing"></script>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


	<script src="${pageContext.request.contextPath}/resources/js/store/storeView.js"></script>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">
    <title>Store</title>
</head>

<body>
<nav class="navbar navbar-light light_green" style="background-color: aquamarine">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div>
        <h5 class="up_down_center">STORE MANAGE</h5>
    </div>

    <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/resources/icons/home-solid.svg" class="home" alt="no picture">
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/sm/{1]/order">주문 내역</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/ALL/1/qna">1:1 문의</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>


<div class="container-fluid text-center" style="margin-top: 15pt">
    <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-fluid img-title" alt="no title"/>
    <h2 style="font-family: 'Do Hyeon';">매장 상세</h2>
</div>

<hr style="margin-top: 0"/>

<div class="container-fluid">

    <div class="text-center" style="margin-bottom: 20pt">

        <img src="${pageContext.request.contextPath}/resources/images/${store.photo}" width="90%" class="rounded" alt="no picture">

    </div>

    <div class="container-fluid text-left">
        <div class="form-group">
            <label for="storeNumber">사업자 번호</label>
            <div id="storeNumber" class="card card-body">
                ${store.businessNumber}
            </div>
        </div>

        <div class="form-group">
            <label for="storeName">매장명</label>
            <div id="storeName" class="card card-body">
                ${store.name}
            </div>
        </div>

        <div class="form-group">
            <label for="storeCategory">매장 대표 카테고리</label>
            <div id="storeCategory" class="card card-body">
                ${store.category.name}
            </div>
        </div>

        <div class="form-group">
            <label for="address">매장주소</label>
            <div id="address" class="card card-body" data-latitude="${store.latitude }" data-longitude="${store.longitude }">
				<div id="map" class="card card-body" style="height: 350pt">
					지도
				</div>
            </div>
        </div>

        <div class="form-group">
            <label for="phoneNumber">매장 전화번호</label>
            <div id="phoneNumber" class="card card-body">
                <a href="tel:${store.phone}">${store.phone }</a>
            </div>
        </div>

        <div class="form-group">
            <label for="storeTime">매장 운영 시간</label>
            <div id="storeTime" class="card card-body">
                ${store.operateTime}
            </div>
        </div>

        <div class="form-group">
            <label for="inputContent">매장 설명</label>
            <div id="inputContent" class="card card-body">
                ${store.introduction}
            </div>
        </div>

        <div class="margin_first">
        <c:choose>
        	<c:when test="${store.state.name eq '승인' || store.state.name eq '거부'}">
	           <a href="${pageContext.request.contextPath}/sm/update/store">
	            <button type="button" class="btn btn-success two_button">매장 수정 하기</button>
	           </a>
           </c:when>
           <c:otherwise>
           	 <button type="button" id="updateBtn" class="btn btn-success two_button">매장 수정 하기</button>
           </c:otherwise>
        </c:choose>
            <button id="deleteStoreBtn" type="button" class="btn btn-success two_button float-right">매장 폐점 하기</button>
        </div>
    </div>
</div>

<hr/>

<div class="last_block"></div>
</body>
</html>