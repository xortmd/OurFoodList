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
 
  
  String log =null;
  if(session.getAttribute("log") != null){
	  log = (String)session.getAttribute("log");
  }
  

  
  
  %>

<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resources/reviewForm.css" />

<title>
	<%//~의 가게 %>
</title>
</head>
<body>
	<!-- 리뷰 작성 폼 -->
	<form class="write" action="ReviewWrite">
		<input type="hidden" name="code" value="<%=code%>"> 
		<select name="give_grade" id="give_grade">
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
		</select> 
		<input type="text" name="user_id" value="<%=log%>" readonly>
		<br>
		<input type="text" placeholder="내용을 입력해주세요." class="text"
			name="coment" required>
		<div class="button">
			<input type="submit" class="reviewSubmit" value="리뷰 작성" onclick="alert('리뷰 작성 완료')">
			<input type="button" onclick="location.href='restaurantSearchForm'" value="돌아가기">
		</div>
	</form>
	<hr>

	<!-- 리뷰 로드 폼 -->
	<%for(ReviewDto review : list ){ %>

	<form class="read" method="post">	
		<input type="hidden" name="no" value="<%=review.getNo()%>"> 
		<p>작성일자 <input type="text" name="reg_date" value="<%=review.getReg_date()%>"></input>
		<p>작성자 <input type="text" id="user_id" name="user_id" value="<%=review.getUser_id() %>" readonly></p>
		<p>별점 :<% double gd = review.getGive_grade();
		if(gd == 5.0){%>
			★★★★★
		<% } else if(gd == 4.5){%>
			★★★★☆
		<% } else if(gd == 4.0){%>
			★★★★
		<% } else if(gd == 3.5){%>
			★★★☆
		<% } else if(gd == 3.0){%>
			★★★
		<% } else if(gd == 2.5){%>
			★★☆
		<% } else if(gd == 2.0){%>
			★★
		<% } else if(gd == 1.5){%>
			★☆
		<% } else if(gd == 1.0){%>
			★
		<% } else if(gd == 0.5){%>
			☆
		<%}%>

		<p>내용</p>
		<input type="text" value="<%=review.getComent() %>" id="coment"	name="coment" readonly>
		<hr>
		<%}%>
		

		<%//if(log != null) {%>
		<%//if(log.equals(review.getUser_id())) { %>
		<!--   <input type="button" onclick="location.href='boardForm'" value="돌아가기">-->
		<!-- <input type="button"  value="글삭제" onclick="location.href='reviewDeleteForm.jsp?no=<%//review.getNo()%>'" name="delete" > -->
		<%//} 
              // } %>

	</form>





</body>
</html>