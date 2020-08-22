<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">


<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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

<script type="text/javascript" charset="UTF-8">
    	sessionStorage.setItem("contextPath","${pageContext.request.contextPath}");
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">
    <script src="${pageContext.request.contextPath}/resources/js/store/menuView.js"></script>
    <!-- <script src="${pageContext.request.contextPath}/resources/js/user/menuView.js"></script>  -->

    <title>Menu</title>
</head>

<body>

<nav class="navbar navbar-light light_green" style="background-color: aquamarine">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!--사용자 위치-->
    <div class="btn-group">
        <button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">
            <span>서울시 봉천동</span>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">주소 변경하기</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">현재 위치 기준</a>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/resources/icons/home-solid.svg" class="home" alt="no picture">
    </a>

    <!--메뉴-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/sm/WAIT/1/order">주문내역 보기</a>
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

<!-- storeId, menuId가 data로 세팅되어야함 -->
<div class="container-fluid" data-storeId="38" data-menuId="55">

    <div class="container-fluid">
    <form action="${pageContext.request.contextPath}/sm/${storeId}/${menuId}/update/menu" method="post" enctype="multipart/form-data">
        <div class="card card-body" style="margin-top: 15pt;margin-bottom: 15pt;">
            <img src="${pageContext.request.contextPath}/resources/images/chicken.png" width="100%" class="rounded" alt="no picture">
        </div>
        
       <div class="form-group">
            
            <img src="${pageContext.request.contextPath}/resources/images/${menu.photo}" width="100%" class="rounded" alt="no picture">
            <input type="file" id="exampleFormControlInput1" name="photo"
					value="${menu.photo}" />
            <!--  <div id="menuPhoto">
               <input type="file" id="menuPhoto" name="photo" value="${menu.photo}"/>
            </div>-->
        </div>

        <div class="form-group">
            <label for="menName">메뉴명</label>
            <div class="card card-body">
            	<input type="text" class="form-control" name="name" id="menName" readonly value="${menu.name}">
            </div>
        </div>

        <div class="form-group">
            <label for="menuAmount">재고 수량</label>
            <div class="card card-body">
            	<input type="text" class="form-control" name="amount" id="menuAmount"  readonly value="${menu.amount}">
            </div>
        </div>

        <div class="form-group" style="margin-bottom: 20pt">
            <label for="offerType">제공방식</label>
            <div class="card card-body">
                <input type="text" class="form-control" name="type" id="menuOfferType"  readonly value="${menu.offerType.id}">
            </div>
        </div>

        <div class="form-group">
            <label for="price">메뉴 가격</label>
            <div class="card card-body">
            <input type="text" class="form-control" name="price" id="menuPrice" readonly value="${menu.price}">
            </div>
        </div>
        <div class="form-group">
            <label for="price">메뉴 태그</label>
            <div class="card card-body">
            <input type="text" class="form-control" name="tag" id="menuTag" readonly value="${menu.tag}">
            </div>
        </div>

        <div class="form-group">
            <label for="menuDescription">메뉴 설명</label>
            <div class="card card-body">
             <input type="text" class="form-control" id="menuDescription" name="description" readonly value="${menu.description}">

            </div>
        </div>

        <div class="form-group" style="margin-bottom: 40pt">
            <label for="notice">메뉴 유의 사항</label>
            <div class="card card-body" id="notice" >
             <input type="text" class="form-control" id="menuNotice" name="notice" readonly value="${menu.notice}">
            </div>
        </div>
        

         <a href="javascript:history.back()">
        <button style="width: 32%;" id="historyBack" type="button" class="btn btn-success three_button" >뒤로가기</button>
        </a>
          
        <button style="width: 32%;" id="menuUpdate" type="submit" class="btn btn-success three_button">수정하기</button>
         
        <button style="width: 32%;" id="menuDelete" type="button" class="btn btn-success three_button" method="delete" action="menu" >삭제하기</button>
        <input type="hidden" id="storeId" name="storedId" value="${storeId}">
        <input type="hidden" id="menuId" name="menuId" value="${menuId}">
      </form>

    </div>
    
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>