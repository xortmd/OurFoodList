<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>검색</title>
	<link rel="stylesheet" href="resources/header.css">
</head>
<body>
	<%
	String log = null;
	if (session.getAttribute("log") != null) {
		log = (String) session.getAttribute("log");
	}
	%>
	<header class="header1">
			<div class="navbar1_logo">
				<a href="index">FoodList</a>
			</div>
			<ul class="navbar1_manu">
<!-- 				<li><a href="index"></a>Home</li> -->
				<li><a href="restaurantSearchForm">가게찾기</a></li>
				<li><a href="restaurantCreateForm">가게등록</a></li>
				<li><a href="boardForm">커뮤니티</a></li>
				<li><a href="noticeForm">공지사항</a></li>
			</ul>
			<ul class="navbar1_links">
				<%
				if (log == null) {
				%>
				<li><a href="login">로그인</a></li>
				<li><a href="login">회원가입 </a></li>
				<%
				} else {
				%>
				<li><a href="myPageForm">마이페이지</a></li>
				<li><a href="logout">로그아웃</a></li>
				<%
				}
				%>
			</ul>
			<a href="#" class="navbar1_manubtn">메뉴</a>
		</header>
		<script>
        const manubtn1 = document.querySelector('.navbar1_manubtn');
        const manu1 = document.querySelector('.navbar1_manu');
        const links1 = document.querySelector('.navbar1_links');

        manubtn1.addEventListener('click', () =>{
            manu1.classList.toggle('active');
            links1.classList.toggle('active');
        });
    </script>
	
</body>
</html>