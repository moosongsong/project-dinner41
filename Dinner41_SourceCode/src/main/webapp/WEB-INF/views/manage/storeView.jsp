<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
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
        <h5 class="up_down_center">MANAGER</h5>
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

<div class="container-fluid">
    <div class="text-center" style="padding: 15pt">
        <h2>${store.name}</h2>
    </div>
</div>

<hr style="margin-top: 0"/>

<div class="container-fluid">
    <div class="text-center" style="margin-bottom: 20pt">
        <img src="${pageContext.request.contextPath}/resources/images/sweet-restaurant.jpg" width="90%" class="rounded" alt="no picture">
    </div>

    <hr style="margin-top: 0"/>

    <div class="container-fluid">
        <h5>점주 정보</h5>
        <table style="width: 100%">
            <tbody style="width: 100%">
            <tr>
                <td>점주명</td>
                <td>${store.user.name}</td>
            </tr>

            <tr>
                <td>전화번호</td>
                <td>${store.user.phone}</td>
            </tr>

            <tr>
                <td>거주주소</td>
                <td>${store.user.address}&nbsp;${store.user.subAddress} </td>
            </tr>

            </tbody>
        </table>
    </div>

    <hr/>

    <div class="container-fluid">
        <h5>
            매장 정보
            <c:if test="${store.state.id eq 1}">
                <span class="badge badge-primary">${store.state.name}</span>
            </c:if>
            <c:if test="${store.state.id eq 2}">
                <span class="badge badge-success">${store.state.name}</span>
            </c:if>
            <c:if test="${store.state.id eq 3}">
                <span class="badge badge-danger">${store.state.name}</span>
            </c:if>
            <c:if test="${store.state.id eq 4 || store.state.id eq 5 || store.state.id eq 6}">
                <span class="badge badge-dark">${store.state.name}</span>
            </c:if>
        </h5>
        <table style="width: 100%">
            <tbody style="width: 100%">
            <tr>
                <td>사업자 번호</td>
                <td>${store.businessNumber}</td>
            </tr>

            <tr>
                <td>매장명</td>
                <td>${store.name}</td>
            </tr>

            <tr>
                <td>매장 카테고리</td>
                <td>${store.category.name}</td>
            </tr>

            <tr>
                <td>매장 전화번호</td>
                <td>${store.phone}</td>
            </tr>

            <tr>
                <td>매장 주소</td>
                <td>${store.address}</td>
            </tr>

            <tr>
                <td>매장 상세 주소</td>
                <td>${store.subAddress}</td>
            </tr>

            <tr>
                <td>매장 운영시간</td>
                <td>${store.operateTime}</td>
            </tr>

            <tr>
                <td>매장 소개</td>
                <td>${store.introduction}</td>

            </tr>

            </tbody>
        </table>
    </div>

    <hr/>

    <br/>

    <c:if test="${store.state.id eq 1}">
        <div class="form-group">
            <a href="${pageContext.request.contextPath}/ad/approve/${store.id}/store">
                <button type="button" class="btn btn-outline-success btn-block">매장 승인 하기</button>
            </a>
            <form method="post" action="${pageContext.request.contextPath}/ad/reject/${store.id}/store">
                <textarea class="form-control" placeholder="매장 승인 거부를 위해선 이유를 기입해주세요." style="margin-top: 7pt; margin-bottom: 3pt" name="content"></textarea>
                <button type="submit" class="btn btn-outline-success btn-block">매장 승인 거부</button>
            </form>
        </div>
    </c:if>
    <c:if test="${store.state.id eq 2}">
        <form method="post" action="${pageContext.request.contextPath}/ad/block/${store.id}/store">
            <textarea class="form-control" placeholder="매장 정지를 위해선 이유를 기입해주세요." style="margin-top: 7pt; margin-bottom: 3pt" name="content"></textarea>
            <button type="submit" class="btn btn-outline-success btn-block">매장 정지</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/ad/delete/${store.id}/store">
            <textarea class="form-control" placeholder="매장 삭제를 위해선 이유를 기입해주세요." style="margin-top: 7pt; margin-bottom: 3pt" name="content"></textarea>
            <button type="submit" class="btn btn-outline-success btn-block">매장 삭제</button>
        </form>

    </c:if>
    <c:if test="${store.state.id eq 3 || store.state.id eq 5 || store.state.id eq 6}">
        <div class="container-fluid text-center">
            ${store.state.name} 처리된 매장입니다.
        </div>
    </c:if>
    <c:if test="${store.state.id eq 4}">
        <a href="${pageContext.request.contextPath}/ad/approve/${store.id}/store">
            <button type="button" class="btn btn-outline-success btn-block">매장 재승인 하기</button>
        </a>
    </c:if>
    <div class="margin_first">
        <a href="${pageContext.request.contextPath}/ad/all-/all-/1/store">
            <button type="button" class="btn btn-success btn-block">매장 목록으로</button>
        </a>
    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>