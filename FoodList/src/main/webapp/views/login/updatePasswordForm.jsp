<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- <link rel="stylesheet" href="resources/grid.css"> --> 
<!-- <link rel="stylesheet" href="resources/loginForm.css"> -->
<link rel="stylesheet" href="resources/passwordForm.css">
<title>My Favorite Restaurant</title>
</head> 
	<jsp:include page="/views/header.jsp"/>
<body>

<%
String id = null;
if(request.getParameter("id")!=null){
	id = request.getParameter("id");
	System.out.println("id:"+id);
}
%>
	<!-- 헤더 풋터 아직 만들기 전이라 주석처리 -->
	<section class="Joinup2"> 
		<div class="form-container">
            <h2 style="margin-bottom: 20px; margin-top: 300px;">비밀번호 재설정</h2>
			<form method="post" action="UpdatePassword" class="form">
				<div class="pws" style="margin-top:15px">
					<label>새 비밀번호</label>
					<input type="password" class="password" name="password" placeholder="5~15자 영어, 숫자, 특수문자"><br>
					<input type="hidden" name="id" value="<%=id %>">
				</div>
				<div class="font">
					<font id="checkPassword" size=2></font>
				</div>
				<div class="pws" style="margin-top:15px">
					<label>새 비밀번호 확인</label>
					<input type="password" class="passwordCheck" name="password" placeholder="5~15자 영어, 숫자, 특수문자"><br>
				</div>
				<div class="font">
					<font id="checkPasswordCheck" size=2></font>
				</div>
				
				<input class="signup-btn-wrap" type="button" style="margin-top:25px" onclick="updateInfo(form)" value="비밀번호 수정" class="제출">
			</form>
			<script src="validation.js"></script>
		</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/validationPassword.js"></script>
    <script src="pw.js"></script>
</body>
</html>