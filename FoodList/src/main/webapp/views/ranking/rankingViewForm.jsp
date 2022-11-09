<%@page import="restaurant.RestaurantDao"%>
<%@page import="restaurant.RestaurantDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/gageRanking.css">
</head>
<jsp:include page="/views/header.jsp"/>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	RestaurantDao dao = RestaurantDao.getInstance();
	ArrayList<RestaurantDto> allList = dao.getRestaurantAll();
	String sort = request.getParameter("sort");
	ArrayList<RestaurantDto> list = dao.sortRestaurant(allList, sort);
	%> 
	<div class="search_box">
        <table>
			<thead>
				<tr>
					<th>이미지</th>
					<th>가게명</th>
					<th>전화번호</th>
					<th>음식종류</th>
					<th>작성일자</th>
					<th>평점</th>
					<th>좋아요</th>
					<th>리뷰</th>
				</tr>
			</thead>
			<tbody>
				<%
				for(RestaurantDto restaurant : list) {
				String date = String.valueOf(restaurant.getReg_date());
				%>
				<tr>
					<td><img src=<%=restaurant.getImage_url()%>></td>
					<td><a href="restaurantViewForm?code=<%=restaurant.getCode()%>"><%=restaurant.getRes_name()%></a></td>
					<td><%=restaurant.getRes_phone()%></td>
					<td><%=restaurant.getKind()%></td>
					<td><%=date.substring(0, 10)%></td>
					<td><%=restaurant.getAve_grade()%></td>
					<td><%=restaurant.getLike_cnt()%></td>
					<td><%=restaurant.getReview_cnt()%></td>
				</tr>
				<%}%>
			</tbody>
		</table>
	</div>
</body>
</html>