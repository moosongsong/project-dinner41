<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <link rel="stylesheet" href="../../css/dinner41.css">

    <title>My Page</title>
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

    <img src="../../icons/home-solid.svg" class="home" alt="no picture"/>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">주문내역</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">1:1 문의</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"> </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">로그아웃</a>
            </li>
        </ul>
    </div>
</nav>

<div>
    <div class="container-fluid text-center" style="margin-top: 15pt">
        <img src="../../images/1인의만찬.png" class="img-fluid img-title" alt="no title"/>
        <h4>마이페이지</h4>
    </div>

    <hr/>

    <div class="container-fluid">

        <div class="form-group">
            <label for="userName">이름</label>
            <input type="text" class="form-control " id="userName" readonly>
        </div>

        <div class="form-group">
            <label for="userEmail">이메일</label>
            <input type="email" class="form-control" id="userEmail" readonly>
        </div>

        <div class="form-group">
            <label for="phoneNumber">전화번호</label>
            <div id="phoneNumber">
                <input type="tel" class="form-control" id="phoneNumber1" style="width: 30% ;float: left" readonly/>
                <p style="width: 5% ;float: left;text-align: center">-</p>
                <input type="tel" class="form-control" id="phoneNumber2" style="width: 30% ;float: left" readonly/>
                <p style="width: 5% ;float: left ; text-align: center">-</p>
                <input type="tel" class="form-control" id="phoneNumber3" style="width: 30% ;" readonly/>
            </div>
        </div>

        <div class="form-group">
            <label for="address">거주지</label>
            <div id="address">
                <input type="text" class="form-control margin_up" id="user_address" readonly/>
                <input type="text" class="form-control margin_up" id="user_sub_address" readonly/>
            </div>
        </div>

        <div class="margin_first">
            <button type="button" class="btn btn-success two_button">수정하기</button>
            <button type="button" class="btn btn-success two_button float-right">뒤로가기</button>
        </div>

    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>