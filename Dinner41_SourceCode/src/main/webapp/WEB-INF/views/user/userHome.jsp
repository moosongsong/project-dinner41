<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">
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

    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <script src="${pageContext.request.contextPath}/resources/js/user/userHome.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dinner41.css">

    <title>Home</title>
</head>

<body>

<!--네비게이션 바-->
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
    <a href="${pageContext.request.contextPath}/gm/cart">
        <img src="${pageContext.request.contextPath}/resources/icons/shopping-bag-solid.svg" class="cart"
             alt="no picture">
    </a>

    <!--메뉴-->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/mypage">내정보 보기</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/gm/WAIT/1/order">주문내역 보기</a>
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

<!--검색창-->
<div class="form-inline">
    <div class="col-7" style="padding: 3pt;">
        <input class="form-control" type="search" placeholder="매장명 또는 메뉴명 입력" style="width: inherit" id="search_input"/>
    </div>
    <div class="col-2" style="padding: 3pt 3pt 3pt 0;">
        <button class="btn btn-outline-success btn-block" type="button" id="search_button" style="font-weight:bold;">찾기</button>
    </div>
    <div class="col-3" style="padding: 3pt 3pt 3pt 0;">
        <a href="${pageContext.request.contextPath}/gm/map">
            <button class="btn btn-outline-success btn-block" type="button" id="map_button" style="font-weight:bold;">지도로 검색</button>
        </a>
    </div>
</div>

<!--메인 사진-->
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" style="margin: 0 3pt 3pt;">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="${pageContext.request.contextPath}/resources/images/cake.png" class="rounded d-block w-100"
                 alt="...">
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/resources/images/stake.png" class="rounded d-block w-100"
                 alt="...">
        </div>
        <div class="carousel-item">
            <img src="${pageContext.request.contextPath}/resources/images/chicken.png" class="rounded d-block w-100"
                 alt="...">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<!--카테고리 모음-->
<div>
    <div class="row row-cols-3" style="margin: 5pt; padding-top: 10pt">
        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/all-/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/total.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">전체</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/도시락/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/box.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">도시락</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/밀키트/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/mealKit.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">밀키트</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/한식/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/korean.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">한식</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/일식/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/japan.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">일식</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/분식/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/tpk.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">분식</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/제과/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/bread.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">제과</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/떡/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/ttt.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">떡</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/치킨/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/chicken.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">치킨</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/피자/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/pizza.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">피자</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/반찬/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/side.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">반찬</h5>
                    </div>
                </div>
            </a>
        </div>

        <div class="col mb-4">
            <a href="${pageContext.request.contextPath}/gm/기타/all-/1/store">
                <div class="card h-100 category_label">
                    <div class="card-body text-center trim">
                        <img src="${pageContext.request.contextPath}/resources/categoryImages/etc.png"
                             class="category rounded" alt="no picture">
                        <h5 class="card-title" style="font-family: 'Do Hyeon';">기타</h5>
                    </div>
                </div>
            </a>
        </div>
    </div>
</div>
<hr/>
<div class="last_block"></div>
</body>
</html>
