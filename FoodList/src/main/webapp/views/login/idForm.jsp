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
<link rel="stylesheet" href="resources/idForm.css">		
<title>My Favorite Restaurant</title>
</head> 
	<jsp:include page="/views/header.jsp"/>
<body>
	<!-- 헤더 풋터 아직 만들기 전이라 주석처리 -->
	<section class="idSearch"> 
		<div class="form-container">
            <h2 style="margin-bottom: 20px; margin-top: 300px;">아이디 찾기</h2>
			<form method="post" action="loginForm" class="form">
				<div class="nm">
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
				
				<input class="search-btn-wrap" type="button" style="margin-top:25px" onclick="checkInfo(form)" value="아이디 찾기" class="제출">
			</form>
			<script src="vali_id.js"></script>
		</div>
	</section>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/vali_id.js"></script>
    <script src="pw.js"></script>
</body>
</html>