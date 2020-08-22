<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script type="text/javascript" charset="UTF-8">
    	sessionStorage.setItem("contextPath","${pageContext.request.contextPath}");
    </script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">
<script src="${pageContext.request.contextPath}/resources/js/store/storeHome.js"></script>

<title>Home</title>

</head>

<body>
	<nav class="navbar navbar-light light_green">

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/mypage">내정보 보기</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath }/sm/WAIT/1/order">주문내역 보기</a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/ALL/1/qna">1:1 문의</a></li>
				<li class="nav-item"><a class="nav-link" href="#"> </a></li>
				<li class="nav-item"><a class="nav-link" href="#"> </a></li>
				<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
			</ul>
		</div>
	</nav>

	<div class="container-fluid">
		<div class="text-center" style="padding: 15pt">
			<h2 style="font-family: 'Do Hyeon';">${store.name}</h2>
		</div>
	</div>

	<div class="container-fluid">
		<div class="alert alert-primary text-center">
			<h3 style="font-family: 'Do Hyeon';">${store.openState}</h3>
		</div>
	</div>

	<!--CLOSE일결우 여기를 사용-->
	<!--<div class="container-fluid" >-->
	<!--    <div class="alert alert-danger text-center">-->
	<!--        <h3>CLOSE</h3>-->
	<!--    </div>-->
	<!--</div>-->

	<div class="container-fluid">
		<button id="openBtn" name="OPEN" class="btn btn-primary two_button">오픈</button>
		<button id="closeBtn" name="CLOSE" class="btn btn-danger two_button float-right">마감</button>
	</div>

	<!--카테고리-->
	<div>
		<div class="row row-cols-3" style="margin: 5pt; padding-top: 10pt">
			<div class="col mb-4">
				<a href="${pageContext.request.contextPath}/sm/store">
					<div class="card h-100" id="storeManagement" name="storeManagement">
						<div class="card-body text-center trim">
							<img src="${pageContext.request.contextPath}/resources/categoryImages/store.png" class="rounded category" alt="no picture">
							<h5 style="font-family: 'Do Hyeon'; font-size: 25px;" class="card-title">매장관리</h5>
						</div>
					</div>
				</a>
			</div>
			<div class="col mb-4">
				<a href="${pageContext.request.contextPath}/sm/1/menu/list">
					<div class="card h-100">
						<div class="card-body text-center trim">
							<img src="${pageContext.request.contextPath}/resources/categoryImages/korean.png" class="rounded category" alt="no picture">
							<h5 style="font-family: 'Do Hyeon'; font-size: 25px;" id="menuManage" class="card-title">메뉴관리</h5>
						</div>
					</div>
				</a>
			</div>
			<div class="col mb-4">
				<a href="${pageContext.request.contextPath}/sm/WAIT/1/order">
					<div class="card h-100">
						<div class="card-body text-center trim">
							<img src="${pageContext.request.contextPath}/resources/categoryImages/list.png" class="rounded category" alt="no picture">
							<h5 style="font-family: 'Do Hyeon'; font-size: 25px;" class="card-title">주문내역</h5>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>

	<hr />
	<div class="last_block"></div>
</body>
</html>
