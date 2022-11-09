<%@page import="review.ReviewDto"%>
<%@page import="restaurant.RestaurantDto"%>
<%@page import="restaurant.RestaurantDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="review.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%
ReviewDao dao = ReviewDao.getInstance();
//리뷰로드를 위한 설정
int code = Integer.parseInt(request.getParameter("code"));

RestaurantDao restDao = RestaurantDao.getInstance();
restDao.updateRestaurantForReviewCnt(code);
restDao.updateRestaurantForAVGgrade(code);

ArrayList<ReviewDto> list = dao.getReviewAllByRestoCode(code);

request.setCharacterEncoding("UTF-8");

String log = null;
if (session.getAttribute("log") != null) {
	log = (String) session.getAttribute("log");
}
%>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resources/reviewForm.css" />

<title>
	<%
	//~의 가게
	%>
</title>
</head>
<body>
	<!-- 리뷰 작성 폼 -->
	<div class="review_box">
		<%
		if (session.getAttribute("log") != null) {
			log = (String) session.getAttribute("log");
		%>
		<form class="write" action="ReviewWrite">
			<input type="hidden" name="code" value="<%=code%>"> <select
				name="give_grade" id="give_grade">
				<option value="5" selected>★★★★★</option>
				<option value="4.5">★★★★☆</option>
				<option value="4">★★★★</option>
				<option value="3.5">★★★☆</option>
				<option value="3">★★★</option>
				<option value="2.5">★★☆</option>
				<option value="2">★★</option>
				<option value="1.5">★☆</option>
				<option value="1">★</option>
				<option value="0.5">☆</option>
			</select> <input type="text" name="user_id" id="user_id" value="<%=log%>"
				readonly> <br>
			<textarea name="coment" placeholder="내용을 입력해주세요." required></textarea>
			<div class="button">
				<input type="submit" class="reviewSubmit" id="button" value="리뷰 작성"
					onclick="alert('리뷰 작성 완료')"> <input type="button"
					onclick="location.href='restaurantSearchForm'" id="button" value="돌아가기">
			</div>
		</form>
		<%
		}
		%>
		<br>
		<hr>
		<br>

		<!-- 리뷰 로드 폼 -->
		<%
		for (ReviewDto review : list) {
		%>

		<form class="read" method="post">
			<input type="hidden" name="no" value="<%=review.getNo()%>">
			<p>
				작성자: <span><%=review.getUser_id()%></span> | 별점 :
				<%
			double gd = review.getGive_grade();
			if (gd == 5.0) {
			%>
				★★★★★
				<%
			} else if (gd == 4.5) {
			%>
				★★★★☆
				<%
			} else if (gd == 4.0) {
			%>
				★★★★
				<%
			} else if (gd == 3.5) {
			%>
				★★★☆
				<%
			} else if (gd == 3.0) {
			%>
				★★★
				<%
			} else if (gd == 2.5) {
			%>
				★★☆
				<%
			} else if (gd == 2.0) {
			%>
				★★
				<%
			} else if (gd == 1.5) {
			%>
				★☆
				<%
			} else if (gd == 1.0) {
			%>
				★
				<%
			} else if (gd == 0.5) {
			%>
				☆
				<%
			}
			%>
				| 작성일자:
				<%=review.getReg_date()%></p>
			<textarea name="coment" id="coment" readonly><%=review.getComent()%></textarea>
		</form>
		<br>
		<%
		}
		%>
	</div>





</body>
</html>