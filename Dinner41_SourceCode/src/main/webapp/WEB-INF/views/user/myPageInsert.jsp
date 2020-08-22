<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<!-- DaumPostcode import -->
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<!-- kakaoMap Api -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc023166bbc9c4e8ae23818cf48006fe&libraries=services,clusterer,drawing"></script>
    

    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
	<script src="${pageContext.request.contextPath }/resources/js/user/mypageInsert.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/dinner41.css">

    <title>My Page</title>
</head>

<body>
<nav class="navbar navbar-light light_green">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!--사용자 위치-->
    <div class="btn-group">
        <button type="button" class="btn btn-outline-dark dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <span>서울특별시 봉천동</span>
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
    <!--메뉴-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath }/gm/{1]/order">주문 내역</a>
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

<!--Content-->
<div>
    <!--제목-->
    <div class="container-fluid text-center" style="margin-top: 15pt">
        <img src="../../images/1인의만찬.png" class="img-title" alt="no title"/>
        <h4 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 30px;">마이페이지</h4>
    </div>

    <hr/>

    <!--내정보 수정 폼-->
<form action="${pageContext.request.contextPath }/mypage" id="update_form" method="post">
    <div class="container-fluid">

        <div class="form-group">
            <label for="userName">이름</label>
            <input type="text" name="name" class="form-control " id="userName" value="${loginUser.name }">
        </div>

        <div class="form-group">
            <label for="userEmail">이메일</label>
            <input type="email" name="email" class="form-control" id="userEmail" value="${loginUser.email }" readonly>
        </div>
<%
	kr.co.dinner41.vo.UserVO user=(kr.co.dinner41.vo.UserVO)session.getAttribute("loginUser");
	String phone=user.getPhone();
	int phoneLength=phone.length();
	String phone1=phone.substring(0, 3);
	String phone2=phone.substring(3, phoneLength-4);
	String phone3=phone.substring(phoneLength-4,phoneLength);
%>

        <div class="form-group">
            <label for="phoneNumber">전화번호</label>
            <div id="phoneNumber">
                <input type="tel" value="<%=phone1 %>" name="phone1" class="form-control" id="phoneNumber1" style="width: 30% ;float: left" >
                <p style="width: 5% ;float: left;text-align: center">-</p>
                <input type="tel" value="<%=phone2 %>" name="phone2" class="form-control" id="phoneNumber2" style="width: 30% ;float: left" >
                <p style="width: 5% ;float: left ; text-align: center">-</p>
                <input type="tel" value="<%=phone3 %>" name="phone3" class="form-control" id="phoneNumber3" style="width: 30% ;" >
            </div>
        </div>

        <div class="form-group">
            <label for="address">거주지</label>
            <div id="address">
                <button type="button" class="btn btn-success two_button" id="search_button">주소 찾기</button>
                <input type="text" name="address" value="${loginUser.address }" class="form-control margin_up" placeholder="주소" id="user_address"/>
                <input type="text" name="subAddress" value="${loginUser.subAddress }" class="form-control margin_up" placeholder="상세주소" id="user_sub_address"/>
				<div id="wrap" style="display:none;border:2px solid #CFE3A1;width:503px;height:300px;margin:5px 0;position:absolute; overflow:auto;"></div>
				<input type="hidden" name="latitude" id="user_latitude" name="latitude" value=""/>
				<input type="hidden" name="longitude" id="user_longitude" name="longitude" value=""/>
            </div>
        </div>

		<!-- 비밀번호 수정버튼을 누르면 비밀번호 수정하는 걸로 바꿈  -->
        <button type="button" id="update_password_button" class="btn btn-success two_button" style="margin-bottom: 20pt;">비밀번호 수정하기</button>

<!--  
        <div class="form-group">
            <label for="exampleInputPassword1">비밀번호</label>
            <input type="password" name="newPassword" class="form-control" id="exampleInputPassword1">
        </div>

        <div class="form-group" style="margin-bottom: 20pt">
            <label for="exampleInputPassword2">비밀번호 확인</label>
            <input type="password" name="newPasswordConfirm" class="form-control" id="exampleInputPassword2">
            <small id="passhelp" class="form-text text-muted">내정보 수정을 위해서는 비밀번호 입력이 필요합니다.</small>
        </div>

-->
        <div id="InputNewPassword" class="form-group" style="display:none">
            <label for="exampleInputPassword1">비밀번호</label>
            <input type="password" name="newPassword" class="form-control" id="exampleInputPassword1">
        </div>

        <div id="InputNewPasswordConfirm" class="form-group" style="margin-bottom: 20pt; display:none">
            <label for="exampleInputPassword2">비밀번호 확인</label>
            <input type="password" name="newPasswordConfirm" class="form-control" id="exampleInputPassword2">
            <small id="passhelp" class="form-text text-muted">내정보 수정을 위해서는 비밀번호 입력이 필요합니다.</small>
        </div>

        <div class="margin_first">
            <button type="button" id="complete_update_button" class="btn btn-success two_button">수정완료</button>
            <button type="button" id="goBackButton" class="btn btn-success two_button float-right">수정취소</button>
        </div>
    </div>
</form>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>
