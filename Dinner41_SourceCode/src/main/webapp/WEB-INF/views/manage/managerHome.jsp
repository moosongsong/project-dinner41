<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

    <title>Manager Home</title>

</head>

<body>
<nav class="navbar navbar-light light_green">

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
                <a class="nav-link" href="${pageContext.request.contextPath }/ad/{1]/order">매장 관리</a>
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
        <h2>Manager Home</h2>
    </div>
</div>

<div>
    <div class="row row-cols-3" style="margin: 5pt; padding-top: 10pt">
        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/ad/all-/all-/1/store">
            	<div class="card h-100">
                	<div class="card-body text-center trim">
                    	<img src="${pageContext.request.contextPath}/resources/icons/cogs-solid.svg" class="" alt="..." width="70%" style="margin: 10%">
                    	<h5 class="card-title" style="margin-top: 10pt; font-family: 'Do Hyeon';">매장관리</h5>
                	</div>
            	</div>
            </a>
        </div>
<%--        <div class="col mb-4">--%>
<%--            <div class="card h-100">--%>
<%--                <div class="card-body text-center trim">--%>
<%--                    <img src="${pageContext.request.contextPath}/resources/icons/utensils-solid.svg" class="" alt="no picture" width="60%" style="margin: 10%">--%>
<%--                    <h5 class="card-title">승인 대기</h5>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/ALL/1/qna">
                <div class="card h-100">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/icons/utensils-solid.svg" class="" alt="no picture" width="60%"
                             style="margin: 10%">
                        <h5 class="card-title">1:1 문의</h5>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>

<hr/>
<div class="last_block"></div>
</body>
</html>