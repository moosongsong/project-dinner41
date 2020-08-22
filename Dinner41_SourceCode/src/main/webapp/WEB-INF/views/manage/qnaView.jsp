<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>Q&A</title>
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
            <li class="nav-item active">
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

<div>
    <div class="container-fluid text-center" style="margin-top: 15pt">
        <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-fluid img-title"
             alt="no title"/>
        <h4 style="font-family: 'Do Hyeon';">1:1 문의 답변</h4>
    </div>

    <hr/>

    <form class="container-fluid" method="post">
        <div class="form-group row">
            <label for="kind" class="col-3 col-form-label">문의 유형</label>
            <div class="col-9">
                <input type="text" class="form-control" id="kind" readonly value="${qna.type.name}">
            </div>
        </div>

        <div class="form-group row">
            <label for="inputTitle" class="col-3 col-form-label">제목</label>
            <div class="col-9">
                <input type="text" class="form-control" id="inputTitle" readonly value="${qna.title}">
            </div>
        </div>

        <div class="form-group row">
            <label for="name" class="col-3 col-form-label">작성자</label>
            <div class="col-9">
                <input type="text" class="form-control" id="name" readonly value="${qna.user.name}">
            </div>
        </div>

        <div class="form-group row">
            <label for="user_type" class="col-3 col-form-label">회원구분</label>
            <div class="col-9">
                <input type="text" class="form-control" id="user_type" readonly value="${qna.user.type.name}">
            </div>
        </div>

        <div class="card card-body" style="margin-bottom: 10pt;">
            ${qna.content}
        </div>


        <c:if test="${!empty qna.answerDate}">
            <div class="card card-body" style="margin-bottom: 10pt;">
                ${qna.answerContent}
            </div>
            <a href="${pageContext.request.contextPath}/ALL/1/qna">
                <button type="button" class="btn btn-outline-success btn-block">목록으로</button>
            </a>
        </c:if>
        <c:if test="${empty qna.answerDate}">
            <textarea class="form-control" placeholder="답변을 입력해주세요." rows="10" name="content"></textarea>
            <div style="margin-top: 5pt">
                <button type="submit" class="btn btn-outline-success two_button">답변등록</button>
                <a href="${pageContext.request.contextPath}/ALL/1/qna">
                    <button type="button" class="btn btn-outline-success two_button float-right">등록취소</button>
                </a>
            </div>
        </c:if>
    </form>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>