<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/user/orderList.js"></script>


    <title>Order History</title>
</head>

<body>
<nav class="navbar navbar-light light_green">

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
            <a class="dropdown-item" href="#">현재 위치 기준</a>
        </div>
    </div>

    <!--장바구니 아이콘-->
    <a href="${pageContext.request.contextPath}/gm/cart"> <img
            src="${pageContext.request.contextPath}/resources/icons/shopping-bag-solid.svg" class="cart"
            alt="no picture">
    </a>

    <!--메뉴-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a></li>
            <li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath }/gm/WAIT/1/order">주문내역 보기</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ALL/1/qna">1:1 문의</a></li>
            <li class="nav-item"><a class="nav-link" href="#"> </a></li>
            <li class="nav-item"><a class="nav-link" href="#"> </a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/">홈 화면으로 이동</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
        </ul>
    </div>
</nav>

<div>
    <!--제목-->
    <div class="container-fluid text-center" style="margin-top: 25pt; margin-bottom: 25px;">
        <img src="${pageContext.request.contextPath}/resources/images/1인의만찬.png" class="img-title" alt="no title"/>
        <h4 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 35px;">주문 내역</h4>
    </div>

    <hr style="margin: 0"/>

    <ul class="nav nav-tabs">
        <c:if test="${type eq 'COMP'}">
            <li class="nav-item text-center nav_two"><a class="nav-link"
                                                        style="color: gray; font-family: 'Do Hyeon'; font-size: 25px; font-weight: bold;"
                                                        href="${pageContext.request.contextPath}/gm/WAIT/1/order">주문대기</a>
            </li>
            <li class="nav-item text-center nav_two"><a class="nav-link active"
                                                        style="color: gray; font-family: 'Do Hyeon'; font-size: 25px; font-weight: bold;"
                                                        href="${pageContext.request.contextPath}/gm/COMP/1/order">수령완료</a>
            </li>
        </c:if>
        <c:if test="${type eq 'WAIT'}">
            <li class="nav-item text-center nav_two"><a class="nav-link active"
                                                        style="color: gray; font-family: 'Do Hyeon'; font-size: 25px; font-weight: bold;"
                                                        href="${pageContext.request.contextPath}/gm/WAIT/1/order">주문대기</a>
            </li>
            <li class="nav-item text-center nav_two"><a class="nav-link"
                                                        style="color: gray; font-family: 'Do Hyeon'; font-size: 25px; font-weight: bold;"
                                                        href="${pageContext.request.contextPath}/gm/COMP/1/order">수령완료</a>
            </li>
        </c:if>
    </ul>

    <div class="text-center">
    <c:if test="${empty map}">
            <br/>
        주문 내역이 비어있습니다.
        <br/><br/>
    </c:if>
    </div>

    <c:if test="${not empty map}">
        <!--주문 목록-->
        <c:forEach items="${map}" var="entry" varStatus="i">
            <div class="orderClass" data-orderid=<c:out value="${entry.key.orderId}"/>>
                <ul class="list-group list-group-flush" id="storeId" data-storeId="${entry.key.storeId}">
                    <li class="list-group-item">
                        <div>
                            <div style="color: green; font-weight: bold; font-size: 25px; font-family: 'Do Hyeon'">
                                주문번호 : <span id="orderNumber"><c:out value="${entry.key.orderId}"/></span><br/>
                            </div>
                            <div style="font-size: 20px;">
                            주문일시 : <span><c:out value="${entry.key.order_order_date}"/></span><br/> 
                            </div>
                            <div style="font-size: 20px;">
                            매장명 : <span><c:out value="${entry.key.storeName}"/></span><br/>
                            </div>

                    <div style="font-size: 20px;">
                            주문메뉴 (수량) :
                    <span style="font-weight: bold;">
                            <c:forEach items="${entry.value}" var="menu" varStatus="i">
                                <c:out value="${menu.menuName}"/>
                               (<c:out value="${menu.amount}"/>) 
                        	<c:if test="${!i.last}">/</c:if>
                            </c:forEach>
                            </span>
                            </div>
                            
                            <div style="margin-top: 5pt" id="pickupDate" data-pickupDate="${entry.key.order_pickup_date}">
                                <button id="reviewClick" class="btn btn-outline-success btn-sm" style="width: 32%; font-size: 20px; font-weight: bold;">
                                        리뷰쓰기
                                </button>
                                <button id="storeViewClick" class="btn btn-outline-success btn-sm" style="width: 32%; font-size: 20px; font-weight: bold;">
                                    가게보기
                                </button>
                                <button id="orderDetailView" class="btn btn-outline-success btn-sm" style="width: 32%; font-size: 20px; font-weight: bold;">
                                    주문상세
                                </button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
		<hr/>
        </c:forEach>
    </c:if>

</div>

<hr style="margin-top: 0"/>

<!--pagination-->
<div style="text-align: center">
    <div class="btn-group" role="group">
        <c:forEach var="obj" items="${pages}">
            <c:if test="${obj.pageNumber eq page}">
                <a href="${pageContext.request.contextPath}/sm/${type}/${obj.pageNumber}/order">
                    <button type="button" class="btn btn-success" disabled><c:out
                            value="${obj.showPageName}"></c:out></button>
                </a>
            </c:if>
            <c:if test="${obj.pageNumber ne page}">
                <c:if test="${obj.pageNumber eq 0}">
                    <a href="${pageContext.request.contextPath}/sm/${type}/1/order">
                        <button type="button" class="btn btn-success"><c:out
                                value="${obj.showPageName}"></c:out></button>
                    </a>
                </c:if>
                <c:if test="${obj.pageNumber ne 0}">
                    <a href="${pageContext.request.contextPath}/sm/${type}/${obj.pageNumber}/order">
                        <button type="button" class="btn btn-success"><c:out
                                value="${obj.showPageName}"></c:out></button>
                    </a>
                </c:if>
            </c:if>
        </c:forEach>
    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>
