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
<link rel="stylesheet" href="resources/joinForm.css">
<title>My Favorite Restaurant</title>
</head> 
	<jsp:include page="/views/header.jsp"/>
<body>
	<!-- 헤더 풋터 아직 만들기 전이라 주석처리 -->
	<section class="Joinup"> 
		<div class="form-container">
            <h2 style="margin-bottom: 20px">회원가입</h2>
			<form method="post" action="Join" class="form">
				<div class="idd">
					<p>아이디</p>
					<input type="text" class="id" name="id" placeholder="아이디"><br>
				</div>
					<font id="checkId" size=2></font>
				<div class="pw" style="margin-top:25px">
					<p>비밀번호</p>
					<input type="password" class="password" name="password" placeholder="비밀번호"><br>
				</div>
					<font id="checkPassword" size=2></font>
				<div class="namenm" style="margin-top:25px">
					<p>이름</p>
					<input type="text" class="name" name="name" placeholder="이름"><br>
				</div>
					<font id="checkName" size=2></font>
				<div class="phonep" style="margin-top:25px">
					<p>연락처</p>
					<input type="text" class="phone" name="phone" placeholder="010-xxxx-xxxx"><br>
				</div>
					<font id="checkPhone" size=2></font>
				<div class="dated" style="margin-top:25px">
					<p>생년월일</p>
					<input type="Date" class="birth" name="birth" placeholder=""><br>
				</div>
					<font id="checkBirth" size=2></font>
				<input class="signup-btn-wrap" type="button" onclick="checkJoin(form)" value="회원가입" class="제출">
			</form>
			<script src="validation.js"></script>
		</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/validation.js"></script>
</body>
</html>