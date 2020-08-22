<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
	String path = "../menu/";
%>

<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>

<script type="text/javascript" charset="UTF-8">
    	sessionStorage.setItem("contextPath","${pageContext.request.contextPath}");
</script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dinner41.css">
<script
	src="${pageContext.request.contextPath}/resources/js/store/menuList.js"></script>

<title>Menu</title>
</head>
<body>

	<nav class="navbar navbar-light light_green">

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div>
			<h5 class="up_down_center">STORE MANAGE</h5>
		</div>

		<a href="${pageContext.request.contextPath}/"> <img
			src="${pageContext.request.contextPath}/resources/icons/home-solid.svg"
			class="home" alt="no picture">
		</a>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/sm/WAIT/1/order">주문내역 보기</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ALL/1/qna">1:1 문의</a></li>
				<li class="nav-item"><a class="nav-link" href="#"> </a></li>
				<li class="nav-item"><a class="nav-link" href="#"> </a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
			</ul>
		</div>
	</nav>

	<div>
		<div class="container-fluid" style="margin-top: 15pt; margin-top: 25pt; margin-bottom: 25px;">
			<div class="text-center">
				<h2 style="font-family: 'Do Hyeon'; margin-top: 10pt; font-size: 35px;">메뉴 관리</h2>
			</div>
		</div>

		<c:if test="${empty menus}">
			등록된 메뉴가 없습니다.
		</c:if>

		<c:if test="${not empty menus}">
			<c:forEach items="${menus}" var="menu" varStatus="i">
				<div class="menuList">
					<ul class="list-group list-group-flush" id="data"
						data-storeId="${menu.store.id}" data-menuId="${menu.id}">
						<li class="list-group-item">
							<div class="row">
								<div class="col-3">
									<img
										src="${pageContext.request.contextPath}/resources/images/${menu.photo}"
										width="100%" class="rounded" alt="menu">
								</div>
								<a
									href="${pageContext.request.contextPath}/${menu.store.id}/${menu.id}/menu/view">
									<div class="col-9" style="padding-left: 0">
										<h5 style="font-weight: bold; font-size: 20px;">
											메뉴명 : <span style="font-family:'Do Hyeon'; font-size:23px; color:green"><c:out value="${menu.name}" /></span>
										</h5>
								</a>
								<div style="margin-top: 5pt; margin-top: 5pt; font-size: 17px;">
									금액 : <span><c:out value="${menu.price}" /></span><br /> 수량 : <span
										id="num"><c:out value="${menu.amount}" /></span> &nbsp; <i
										id="minusButton" class="fas fa-minus-square pos"></i> <i
										id="plusButton" class="fas fa-plus-square pos"></i>&nbsp;&nbsp;
									<button id="countUpdate" style="font-weight: bold;"
										class="btn btn-outline-success btn-sm trim">등록</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</c:forEach>
		</c:if>


		<hr style="margin-top: 0" />

		<div style="text-align: center">
			<div class="btn-group" role="group">
				<c:forEach var="obj" items="${pages}">
					<c:if test="${obj.pageNumber eq page}">
						<a
							href="${pageContext.request.contextPath}/sm/${obj.pageNumber}/menu/list">
							<button type="button" class="btn btn-success" disabled>
								<c:out value="${obj.showPageName}"></c:out>
							</button>
						</a>
					</c:if>
					<c:if test="${obj.pageNumber ne page}">
						<c:if test="${obj.pageNumber eq 0}">
							<a href="${pageContext.request.contextPath}/sm/1/menu/list">
								<button type="button" class="btn btn-success">
									<c:out value="${obj.showPageName}"></c:out>
								</button>
							</a>
						</c:if>
						<c:if test="${obj.pageNumber ne 0}">
							<a
								href="${pageContext.request.contextPath}/sm/${obj.pageNumber}/menu/list">
								<button type="button" class="btn btn-success">
									<c:out value="${obj.showPageName}"></c:out>
								</button>
							</a>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
		</div>

		<!--  	<div style="text-align: center">
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-success"></button>
				<button type="button" class="btn btn-success" disabled>1</button>
				<button type="button" class="btn btn-success">2</button>
				<button type="button" class="btn btn-success">3</button>
				<button type="button" class="btn btn-success">4</button>
				<button type="button" class="btn btn-success">>></button>
			</div>
		</div>
		-->
		<div class="container-fluid margin_first">
			<a href="${pageContext.request.contextPath}/sm/menu">
				<button style="font-size: 20px; font-weight: bold;" type="button" class="btn btn-outline-success btn-block">메뉴
					추가 하기</button>
			</a>
		</div>
	</div>
	<hr />
	<div class="last_block"></div>
</body>
</html>