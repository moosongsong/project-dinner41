<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
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

    <style>
        #star_grade a{
            text-decoration: none;
            color: gray;
        }
        #star_grade a.on{
            color: red;
        }
        #star_grade a:hover{
            cursor: pointer;
        }
    </style>

    <title>Review</title>
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
            <li class="nav-item">
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

<div>
    <div class="container-fluid text-center" style="margin-top: 15pt">
        <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-title" alt="no title"/>
        <h4 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 30px;">리뷰 쓰기</h4>
    </div>

    <hr/>

    <div class="container-fluid">
        <h5>식당이름 : <span><c:out value="${store.name}"></c:out></span></h5>
        <div>
            <c:forEach var="vo" items="${menus}">
                <div class="row">
                    <div class="col-3">주문 메뉴 :</div>
                    <div class="col-3">${vo.menuName}</div>
                    <div class="col-2">${vo.menuAmount}개</div>
                    <div class="col-4">${vo.menuTotalPrice}원</div>
                </div>
            </c:forEach>
        </div>
    </div>

    <hr/>

    <form class="container-fluid" method="post">
        <span>식사는 어떠셨나요? 별점으로 만족도를 알려주세요</span>
        <p id="star_grade">
            <a id="1">★</a>
            <a id="2">★</a>
            <a id="3">★</a>
            <a id="4">★</a>
            <a id="5">★</a>
        </p>
        <input type="hidden" name="score" value="0" id="score">
        <script>
            $('#star_grade a').click(function(){
                $(this).parent().children("a").removeClass("on");  /* 별점의 on 클래스 전부 제거 */
                $(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
                return false;
            });
            $('#1').click(function (){
               $('#score').val(1);
            });
            $('#2').click(function (){
                $('#score').val(2);
            });
            $('#3').click(function (){
                $('#score').val(3);
            });
            $('#4').click(function (){
                $('#score').val(4);
            });
            $('#5').click(function (){
                $('#score').val(5);
            });
        </script>
        <textarea class="form-control" rows="7" id="review_content" name="content"></textarea>
        <div class="margin_first">
            <button type="submit" class="btn btn-outline-success two_button" id="review_button">리뷰등록</button>
            <button type="button" class="btn btn-outline-success two_button float-right" id="cancel_button">등록취소</button>
        </div>
    </form>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>