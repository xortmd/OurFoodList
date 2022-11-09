<%@page import="review.ReviewDao"%>
<%@page import="user.UserDao"%>
<%@page import="notice.NoticeDao"%>
<%@page import="restaurant.RestaurantDao"%>
<%@page import="restaurant.RestaurantDto"%>
<%@page import="board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>맛집</title>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/index.css">
</head>
<body>
	<%
	String log = null;
	if (session.getAttribute("log") != null) {
		log = (String) session.getAttribute("log");
	}
	
	RestaurantDao rDao = RestaurantDao.getInstance();
	ArrayList<RestaurantDto> allList = rDao.getRestaurantAll();
	
	BoardDao bDao = BoardDao.getInstance();
	NoticeDao nDao = NoticeDao.getInstance();
	UserDao uDao = UserDao.getInstance();
	ReviewDao vDao = ReviewDao.getInstance(); 
	
	
	int totalView = nDao.getTotalView_CntOnNotice() + bDao.getTotalView_CntOnBoard();
	%>
	<div class="wrap">
		<div class="intro_bg">
			<div class="header">
				<div class="searchArea">
					<form>
						<input type="search" placeholder="search"> <span>검색</span>
					</form>
				</div>
				<ul class="nav">
<!-- 					<li><a href="ex05.html">HOME</a></li> -->
					<li><a href="restaurantSearchForm">맛집찾기</a></li>
					<li><a href="restaurantCreateForm">맛집등록</a></li>
					<li><a href="boardForm">커뮤니티</a></li>
					<li><a href="noticeForm">공지사항</a><li>
					<%
					if (log == null) {
					%>
					<li><a href="loginForm">로그인</a></li>
					<li><a href="joinForm">회원가입</a></li>
					<%
					} else {
					%>
					<li><a href="myPageForm">마이페이지</a></li>
					<li><a href="logout">로그아웃</a></li>
					<%
					}
					%>
				</ul>
			</div>
			<div class="intro_text">
				<h1>오늘 뭐 먹을까?</h1>
				<h4 class="contents1">오늘은 무엇을 먹어야 잘 먹었다고 소문이 나는 것인가</h4>
			</div>
		</div>
	</div>
	<!-- intro end-->
	<ul class="amount">
		<li>
			<div>
				<div class="contents1">등록된 가게 수</div>
				<div class="result"><%=rDao.getCountRestaurant()%></div>
			</div>
		</li>
		<li>
			<div>
				<div class="contents1">등록된 리뷰 수</div>
				<div class="result"><%=vDao.getReviewCount() %></div>
			</div>
		</li>
		<li>
			<div>
				<div class="contents1">전체 조회 수</div>
				<div class="result"><%=totalView%></div>
			</div>
		</li>
		<li>
			<div>
				<div class="contents1">전체 회원 이용 수</div>
				<div class="result"><%=uDao.getCountUser()%></div>
			</div>
		</li>
	</ul>
	<!-- amount end -->

	<div class="main_text0">
		<h1></h1>
		<!-- <div class="contents1"></div> -->
		<ul class="icons">
			<li>
				<div class="contents1_bold">평점 top10</div>
				<div>
			        <table class="ranking_box">
						<thead>
							<tr>
								<th>&nbsp;랭킹&nbsp;</th>
								<th>&nbsp;가게명&nbsp;</th>
								<th>&nbsp;음식종류&nbsp;</th>
								<th>&nbsp;평점&nbsp;</th>
								<th>&nbsp;좋아요&nbsp;</th>
								<th>&nbsp;리뷰&nbsp;</th>
							</tr>
						</thead>
						<tbody class="ranking_tbody">
							<%
							ArrayList<RestaurantDto> rList = rDao.sortRestaurant(allList, "평점순");
							for(int i = 0; i < 10; i++) {%>
							<tr>
								<td><%=(i+1)%>.</td>
								<td><%=rList.get(i).getRes_name()%></td>
								<td><%=rList.get(i).getKind()%></td>
								<td><%=rList.get(i).getAve_grade()%></td>
								<td><%=rList.get(i).getLike_cnt()%></td>
								<td><%=rList.get(i).getReview_cnt()%></td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
				<form action="rankingViewForm">
					<input type="hidden" id="sort" name="sort" value="평점순">
					<input type="submit" value="MORE" class="more">
				</form>
			</li>

			<li>
				<div class="contents1_bold">리뷰 top10</div>
				<div>
			        <table class="ranking_box">
						<thead>
							<tr>
								<th>&nbsp;랭킹&nbsp;</th>
								<th>&nbsp;가게명&nbsp;</th>
								<th>&nbsp;음식종류&nbsp;</th>
								<th>&nbsp;평점&nbsp;</th>
								<th>&nbsp;좋아요&nbsp;</th>
								<th>&nbsp;리뷰&nbsp;</th>
							</tr>
						</thead>
						<tbody class="ranking_tbody">
							<%
							rList = rDao.sortRestaurant(allList, "리뷰순");
							for(int i = 0; i < 10; i++) {%>
							<tr>
								<td><%=(i+1)%>.</td>
								<td><%=rList.get(i).getRes_name()%></td>
								<td><%=rList.get(i).getKind()%></td>
								<td><%=rList.get(i).getAve_grade()%></td>
								<td><%=rList.get(i).getLike_cnt()%></td>
								<td><%=rList.get(i).getReview_cnt()%></td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
				<form action="rankingViewForm">
					<input type="hidden" id="sort" name="sort" value="리뷰순">
					<input type="submit" value="MORE" class="more">
				</form>
			</li>

			<li>
				<div class="contents1_bold">좋아요 top10</div>
				<div>
			        <table class="ranking_box">
						<thead>
							<tr>
								<th>&nbsp;랭킹&nbsp;</th>
								<th>&nbsp;가게명&nbsp;</th>
								<th>&nbsp;음식종류&nbsp;</th>
								<th>&nbsp;평점&nbsp;</th>
								<th>&nbsp;좋아요&nbsp;</th>
								<th>&nbsp;리뷰&nbsp;</th>
							</tr>
						</thead>
						<tbody class="ranking_tbody">
							<%
							rList = rDao.sortRestaurant(allList, "좋아요순");
							for(int i = 0; i < 10; i++) {%>
							<tr>
								<td><%=(i+1)%>.</td>
								<td><%=rList.get(i).getRes_name()%></td>
								<td><%=rList.get(i).getKind()%></td>
								<td><%=rList.get(i).getAve_grade()%></td>
								<td><%=rList.get(i).getLike_cnt()%></td>
								<td><%=rList.get(i).getReview_cnt()%></td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
				<form action="rankingViewForm">
					<input type="hidden" id="sort" name="sort" value="좋아요순">
					<input type="submit" value="MORE" class="more">
				</form>
			</li>
		</ul>
	</div>
	
	
	
	
	
	
	<div class="footer">
		<div>
			CEO. 마지어려워<br> Addr. 서울특별시 강남구 강남1로 280-3 빌라15층짜리 6차 14F 고객상담실
			Fax/Tel. <br> 02 - 223 -2912~5 <br> COPYRIGHT 2019. TAMO.
			ALL RIGHT RESERVED.
		</div>

	</div>
	<!-- 	</div> -->
</body>
</html>
