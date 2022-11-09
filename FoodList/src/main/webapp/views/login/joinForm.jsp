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
	<section class="Joinup"> 
		<div class="form-container">
            <h2 style="margin-bottom: 20px">회원가입</h2>
			<form method="post" action="Join" class="form">
				<div class="idd">
					<label>아이디</label>
					<input type="text" class="id" name="id" placeholder="아이디"><br>
				</div>
				<div class="font">
					<font id="checkId" size=2></font>
				</div>	
				<div class="pw" style="margin-top:15px">
					<label>비밀번호</label>
					<input type="password" class="password" name="password" placeholder="5~15자 영어, 숫자, 특수문자" ><br>
				</div>
				<div class="font">
					<font id="checkPassword" size=2></font>
				</div>	
				<div class="namenm" style="margin-top:15px">
					<label>이름</label>
					<input type="text" class="name" name="name" placeholder="이름"><br>
				</div>
				<div class="font">
					<font id="checkName" size=2></font>
				</div>
				<div class="phonep" style="margin-top:15px">
					<label>연락처</label>
					<input type="text" class="phone" name="phone" placeholder="010-xxxx-xxxx"><br>
				</div>
				<div class="font">
					<font id="checkPhone" size=2></font>
				</div>
				<div class="dated" style="margin-top:15px">
					<label>생년월일</label>
					<input type="Date" class="birth" name="birth" placeholder=""><br>
				</div>
				<div class="font">
					<font id="checkBirth" size=2></font>
				</div>
				<input class="signup-btn-wrap" type="button" style="margin-top:25px" onclick="checkJoin(form)" value="회원가입" class="제출">
			</form>
			<script src="validation.js"></script>
		</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/validation.js"></script>
</body>
</html>