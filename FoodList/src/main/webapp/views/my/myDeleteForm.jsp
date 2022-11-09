<%@page import="user.UserDto"%>
<%@page import="user.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="resources/myDeleteForm.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>  
	<jsp:include page="/views/header.jsp" />
	<%
	String log = null;
	String user_pw = "";
	if (session.getAttribute("log") != null) {
		log = (String) session.getAttribute("log");
		System.out.println("log : " + log);

		UserDao dao = UserDao.getInstance();
		UserDto user = dao.getUserById(log);
		user_pw = user.getPassword();
	}
	%>
	<nav>
		<jsp:include page="/views/my/sidebar.jsp" />
	</nav>
	<section class="updateUser">
		<div class="form-container">
			<form method="post" action="UserDelete" class="delete_form">
				<h2>회원탈퇴</h2>
				<p>안전한 탈퇴를 위해 비밀번호를 입력해주세요</p>
				<div class="int-area" style="margin-top:15px">
                    <label>아이디</label>
					<input type="text" name="id" class="delete_id" id="id"
						value="<%=log%>" readonly autocomplete="off">
					<!--                 <label for="id"> User name</label> -->
				</div>
				<div class="int-area" style="margin-top:15px">
                    <label>비밀번호</label>
                    <input type="hidden" class="user_pw" value="<%=user_pw%>">
					<input type="password" name="password" class="delete_password" placeholder="비밀번호" id="pw" autocomplete="off" >
				</div>
					<font id="checkInfo" size=2></font><br>							
				<input class="button" type="button" onclick="checkInfo(form)" value="탈퇴" style="cursor: pointer;">
			</form>
		</div>
	</section>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/vali_deleteUser.js"></script>
</body>
</html>