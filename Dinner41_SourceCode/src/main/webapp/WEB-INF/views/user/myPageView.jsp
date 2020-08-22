<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/dinner41.css">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

	<script type="text/javascript" charset="UTF-8">
    	sessionStorage.setItem("contextPath","${pageContext.request.contextPath}");
    </script>
    
    <script src="${pageContext.request.contextPath }/resources/js/user/myPageView.js"></script>

    <title>My Page</title>
</head>

<body>
<nav class="navbar navbar-light light_green">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!--사용자 위치-->
    <div class="btn-group">
        <button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                aria-expanded="false">
            <span>서울특별시 봉천동</span>
        </button>
        <div class="dropdown-menu">
            <a class="dropdown-item" href="#">주소 변경하기</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="${pageContext.request.contextPath}/logout">현재 위치 기준</a>
        </div>
    </div>

    <a href="${pageContext.request.contextPath}/gm/cart">
        <img src="${pageContext.request.contextPath}/resources/icons/shopping-bag-solid.svg" class="cart" alt="no picture">
    </a>
    <!--메뉴-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/gm/WAIT/1/order">주문내역 보기</a>
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

<div>
    <!--제목-->
    <div class="container-fluid text-center" style="margin-top: 15pt">
        <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-title" alt="no title"/>
        <h4 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 30px;">마이페이지</h4>
    </div>

    <hr/>

    <!--내 정보 보기 폼-->
    <div class="container-fluid">
    	<form:form id="update_form" action="${pageContext.request.contextPath }/mypage">

        <div class="form-group">
            <label for="userName">이름</label>
            <input type="text" class="form-control " id="userName" value="${viewTargetUser.name }" readonly>
        </div>

		<br/>
        <div class="form-group">
            <label for="exampleFormControlInput1">이메일</label>
            <input type="email" class="form-control" id="exampleFormControlInput1" value="${viewTargetUser.email }"readonly>
        </div>

        <div class="form-group">
            <label for="phoneNumber">전화번호</label>
            <div id="phoneNumber">
                <input type="tel" class="form-control" id="userPhone" style="width: 30% ;float: left" value="${viewTargetUser.phone }" readonly/>
                <!--  
                <input type="tel" class="form-control" id="phoneNumber1" style="width: 30% ;float: left" readonly/>
                <p style="width: 5% ;float: left;text-align: center">-</p>
                <input type="tel" class="form-control" id="phoneNumber2" style="width: 30% ;float: left" readonly/>
                <p style="width: 5% ;float: left ; text-align: center">-</p>
                <input type="tel" class="form-control" id="phoneNumber3" style="width: 30% ;" readonly/>
-->
            </div>
        </div>

		<br/><br/>
        <div class="form-group">
            <label for="address">거주지</label>
            <div id="address">
                <input type="text" class="form-control margin_up" id="user_address" value="${viewTargetUser.address }" readonly/>
                <input type="text" class="form-control margin_up" id="user_sub_address" value="${viewTargetUser.subAddress }" readonly/>
            </div>
        </div>

        <div class="margin_first">
            <button style="width: 32%;" type="button" id="update_button" class="btn btn-success three_button">수정하기</button>
            <a href="javascript:history.back()">
				<button style="width: 32%;" type="button" id="go_back_button" class="btn btn-success three_button" >뒤로가기</button>
			</a>
            <button style="width: 32%;" type="button" id="delete_button" class="btn btn-success three_button" method="delete" action="mypage">탈퇴하기</button>
            <input type="hidden" id="password" name="password">
        </div>
		</form:form>
    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>