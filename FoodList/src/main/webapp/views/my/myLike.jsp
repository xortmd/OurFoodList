<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="resources/myPageForm.css">
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<title>Insert title here</title>
</head>
<body>   
	<%
	String log = null;
	if (session.getAttribute("log") != null) {
		log = (String) session.getAttribute("log");
		System.out.println("log : " + log);
	}
	%>
	<jsp:include page="/views/header.jsp" />
	<nav>
		<jsp:include page="/views/my/sidebar.jsp" />
	</nav>
	<section class="updateUser">
		<div>
		<input type="hidden" class="id" name="id" value="<%=log%>">
        <table border="1">
			<thead class="title"></thead>
			<tbody class="container"></tbody>
		</table>
	</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/searchLikeByUser.js"></script>
</body>
</html>