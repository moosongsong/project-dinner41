<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
	String path = "../menu/";
%>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
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
	<script src="${pageContext.request.contextPath}/resources/js/store/menuRegister.js"></script>
    <title>Menu</title>
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
<div class="container-fluid" style="margin-top: 15pt">
    <div class="text-center">
        <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-fluid img-title" alt="no title"/>
        <h2 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 35px;">메뉴 등록</h2>
    </div>
</div>
<hr/>

<div class="container-fluid">
    <form id="register_form" class="text-left container-fluid" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/sm/menu">

        <div class="form-group">
            <label for="name">메뉴명</label>
            <input type="text" class="form-control" name="name" id="menuName"/>
        </div>

        <div class="form-group">
            <label for="amount">재고 수량</label>
            <input type="text" class="form-control" name="amount" id="menuAmount">
        </div>

        <div class="form-group">
            <label for="exampleFormControlInput1">메뉴 사진 첨부</label>
            <div id="emailCheckLabel">
                <input type="file" id="menuPhoto" name="photo">
            </div>
        </div>

        <div class="form-group" style="margin-bottom: 20pt">
            <label for="inputKind">제공방식</label>
            <select name="type" id="offerType" class="form-control">
                <option value="PAC">도시락</option>
                <option value="MEA">조리키트</option>
                <option value="COO">완전조리식</option>
            </select>
        </div>

        <div class="form-group">
            <label for="menuMoney">메뉴 가격</label>
            <input name ="price" type="text" class="form-control margin_up" placeholder="메뉴 가격" id="menuPrice"/>
        </div>

        <div class="form-group">
            <label for="tag">메뉴 태그</label>
            <input name="tag" type="tel" class="form-control" id="menuTag">
        </div>

        <div class="form-group">
            <label for="menuDes">메뉴 설명</label>
            <textarea class="form-control" name="introduction" id="menuIntro" placeholder="메뉴에 대해서 설명해주세요." rows="5"></textarea>
        </div>


        <div class="form-group" style="margin-bottom: 40pt">
            <label for="inputContent">메뉴 유의 사항</label>
            <textarea class="form-control" name="notice" id="menuNotice" placeholder="재료, 재료 원산지 알레르기 유발식품에 대해 적어주세요."
                      rows="5"></textarea>
        </div>

        <button id ="menuWrite" type="button" class="btn btn-success two_button">메뉴 등록하기</button>
        
        <button id = "menuWriteCancel" type="button" class="btn btn-success two_button float-right">메뉴 등록 취소하기</button>
    
    </form>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>