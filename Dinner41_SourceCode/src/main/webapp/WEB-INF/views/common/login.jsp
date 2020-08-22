<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<script src="${pageContext.request.contextPath}/resources/js/common/login.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/dinner41.css">


<title>login</title>
<script>
	sessionStorage.setItem("contextPath", "${pageContext.request.contextPath}");
</script>
</head>
<body>
<!--전체-->
<div class="container-fluid">
    <!--한행-->
    <div class="row" style="text-align: center;">
        <!--첫번째 블록-->
        <div style="width: 80%; margin: 20pt auto auto;">
            <!--로고 이미지-->
            <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" width="70%" class="img-fluid" alt="no title"/>
            <!--로그인 입력 폼-->
            <form style="text-align: left; margin-bottom: 15pt" method="post" action="login">
                <!--이메일 입력란-->
                <div class="form-group">
                    <!--라벨의 for와 이메일의 id가 일치해야함-->
                    <label for="user_email">이메일</label>
                    <input type="email" name="email" class="form-control" placeholder="name@example.com" id="user_email">
                </div>
                <!-- error message 출력 -->
                <form:errors path="email" cssStyle=""/>
                <!--비밀번호 입력란-->
                <div class="form-group">
                    <!--라벨의 for와 이메일의 id가 일치해야함-->
                    <label for="user_password">비밀번호</label>
                    <input type="password" name="password" class="form-control" id="user_password">
                </div>
                <!-- error message출력 -->
                <form:errors path="password"/>
                <!--전송 버튼-->
                <button type="submit" class="btn btn-success btn-block" id="login_button">로그인</button>
            </form>
            <button type="button" class="btn btn-outline-success btn-block" id="password_button">비밀번호 찾기</button>
            <button type="button" class="btn btn-outline-success btn-block" id="register_button">회원가입하기</button>
            <div id="modelData" data-resultemail="${resultUser.email}" data-errormessage="${loginErrorMessage }"></div>
        </div>
    </div>
</div>
<div class="last_block"></div>
</body>
</html>
