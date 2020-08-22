<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>

    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc023166bbc9c4e8ae23818cf48006fe&libraries=services,clusterer,drawing"></script>

	<script src="${pageContext.request.contextPath}/resources/js/user/storeView.js"></script>

    <title>Store</title>

    <style>
        .menu:hover {
            background-color: aliceblue;
            cursor: pointer;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-light light_green">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="btn-group">
        <button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">
            <span>${address}</span>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">주소 변경하기</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">현재 위치 기준</a>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/gm/cart">
        <img src="${pageContext.request.contextPath}/resources/icons/shopping-bag-solid.svg" class="cart" alt="no picture">
    </a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">주문내역 보기</a>
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
	            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">홈 화면으로 이동</a></li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>

<div style="margin-top: 10pt">
    <div class="container-fluid">
        <div class="card card-body">
            <h2>${store.name}&nbsp;
                <c:if test="${store.openState eq 'OPEN'}">
                    <span class="badge badge-primary">OPEN</span>
                </c:if>
                <c:if test="${store.openState eq 'CLOSE'}">
                    <span class="badge badge-danger">CLOSE</span>
                </c:if>
            </h2>
            <h4>별점 : <span style="color: red">${score}</span>점!!</h4>
            <h4>전화 : ${store.phone}&nbsp; <i class="fas fa-phone-square"></i></h4>
        </div>
    </div>
    <div class="container-fluid" style="margin-top: 10pt">
        <div class="container-fluid">
            <h5>가게 소개</h5>
            <p>${store.introduction}</p>
            <h5>가게 운영시간</h5>
            <p>${store.operateTime}</p>
            <h5>가게 위치</h5>
            <span>
                ${store.address}&nbsp;
                ${store.subAddress}
            </span>
        </div>
        <div class="card card-body">
			<div id="map" class="card card-body" style="height:150pt" data-latitude="${store.latitude }" data-longitude="${store.longitude }">
				지도
			</div>    
        </div>
    </div>
    <hr/>

    <ul class="nav nav-tabs">
        <c:if test="${type eq 'menu'}">
            <li class="nav-item text-center" style="width: 50%">
                <a class="nav-link active" href="${pageContext.request.contextPath}/gm/${store.id}/menu/store">메뉴</a>
            </li>
            <li class="nav-item text-center" style="width: 50%">
                <a class="nav-link" href="${pageContext.request.contextPath}/gm/${store.id}/review/store">리뷰</a>
            </li>
        </c:if>
        <c:if test="${type eq 'review'}">
            <li class="nav-item text-center" style="width: 50%">
                <a class="nav-link" href="${pageContext.request.contextPath}/gm/${store.id}/menu/store">메뉴</a>
            </li>
            <li class="nav-item text-center" style="width: 50%">
                <a class="nav-link active" href="${pageContext.request.contextPath}/gm/${store.id}/review/store">리뷰</a>
            </li>
        </c:if>
    </ul>

    <ul class="list-group list-group-flush">
        <c:if test="${type eq 'menu'}">
            <c:forEach var="vo" items="${list}">
                <li class="list-group-item">
                    <a href="${pageContext.request.contextPath}/${store.id}/${vo.id}/menu/view" class="menu">
                        <div class="row">
                            <div class="col-3">
                                <img src="${pageContext.request.contextPath}/resources/images/${vo.photo}"
                                     width="100%" class="rounded" alt="no picture">
                            </div>
                            <div class="col-9" style="padding-left: 0">
                                <h5 style="font-weight: bold; font-size: 23px; font-family:'Do Hyeon';">
                                        ${vo.name}
                                </h5>
                                <div>
                                    가격 : <span style="font-size: 17px;">${vo.price}원</span>
                                    <br/>
                                    최대수량 : <span style="font-size: 17px;">${vo.amount}개</span>
                                </div>
                            </div>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </c:if>
        <c:if test="${type eq 'review'}">
            <c:forEach var="re" items="${reviews}">
                <li class="list-group-item">
                    <h5 class="text-center">
                        <span class="badge badge-primary"><c:out value="${re.score}"></c:out>점</span>이에요!
                    </h5>
                    <div class="card card-body">
                        주문 일자 : <c:out value="${re.date}"></c:out>
                        <br/>
                        <span style="font-weight: bold"><c:out value="${re.content}"></c:out></span>
                    </div>
                </li>
            </c:forEach>
        </c:if>
    </ul>
    <hr style="margin: 0"/>

    <div class="container-fluid margin_first">
        <c:if test="${type eq 'review'}">
            <a href="${pageContext.request.contextPath}/gm/${store.id}/1/review">
                <button type="button" class="btn btn-outline-success btn-block">리뷰목록 보러가기</button>
            </a>
        </c:if>
    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>