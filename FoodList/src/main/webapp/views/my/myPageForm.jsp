<%@page import="user.UserDao"%>
<%@page import="user.UserDto"%>
<%@page import="board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
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
<title>MY PAGE</title>
</head>
<body>
	<jsp:include page="/views/header.jsp"/>
	<%
	UserDao dao = UserDao.getInstance();
	ArrayList<UserDto> list = dao.getUserAll();		//	확인용 추후 삭제
	UserDto user = null;
 
	String log = null;
	if (session.getAttribute("log") != null) {
		log = (String) session.getAttribute("log");
		user = dao.getUserById(log);

		String birthDay = user.getBirth().toString().split(" ")[0];
// 		String password = user.getPassword();
// 		String phone = user.getPhone();
	%>
	
	<nav>
        <div class="menu">
            <label for="expand-menu"><div>메뉴</div></label><input type="checkbox" id="expand-menu" name="expand-menu">
            <ul>
                <li><a href="myPageForm" class="item"><div>프로필</div></a></li>
                <li><a href="myBoard" class="item"><div>My Board</div></a></li>
                <li><a href="myReview" class="item"><div>My Review</div></a></li>
                <li><a href="myLike" class="item"><div>My Like</div></a></li>
                <li><a href="#" class="item"><div>설정</div></a></li>
                <li><a href="myDeleteForm" class="item"><div>회원탈퇴</div></a></li>
            </ul>
        </div>
    </nav>
	<section class="updateUser">
		<div class="form-container">
			<h2 style="margin-bottom: 20px">회원정보수정</h2>
			<form method="post" action="UserUpdate" class="form">
				<!-- 아이디(중복확인) 패스워드 패스워드확인 이름 연락처 주소 운전면허번호 주소 (등록일자는 데이터처리할 때 생성 -->
					<div class="id">
						<p>아이디</p>
						<input type="text" class="idCont" name="id" value="<%=user.getId()%>"><br> 
						<input type="hidden" class="password" name="password" value="<%=user.getPassword()%>" >
					</div>
					
					<div class="pw" style="margin-top: 25px">
						<p>기존 비밀번호</p>
						<input type="text" class="passwordOri" placeholder="비밀번호 수정시 작성"><br> 
					</div>
					<font id="checkPasswordOri" size=2></font> 
					
					<div class="pw" style="margin-top: 25px">
						<p>새 비밀번호</p>
						<input type="text" class="passwordNew" name="passwordNew" placeholder="비밀번호 수정시 작성"><br> 
					</div>
					<font id="checkPasswordNew" size=2></font>
					
					<div class="pw" style="margin-top: 25px">
						<p>새 비밀번호 확인</p>
						<input type="text" class="passwordNewCheck" placeholder="비밀번호 수정시 작성"><br> 
					</div>
					<font id="checkPasswordNewCheck" size=2></font>

					<div class="name" style="margin-top: 25px">
						<p>이름</p>
						<input type="text" class="nameNew" name="name" value="<%=user.getName()%>" placeholder="이름"><br> 
					</div>
					<font id="checkName" size=2></font>

					<div class="phone" style="margin-top: 25px">
						<p>연락처</p>
						<input type="text" class="phoneNew" name="phone" value="<%=user.getPhone()%>" placeholder="010-xxxx-xxxx"><br>
						<input type="hidden" class="phone" value="<%=user.getPhone()%>" >
					</div>
					<font id="checkPhone" size=2></font>

					<div class="date" style="margin-top: 25px">
						<p>생년월일</p>
						<input type="Date" class="birth" name="birth" value="<%=birthDay%>" disabled><br> 
					<font id="checkBirth" size=2></font>
					
					</div>
					<input class="signup-btn-wrap" type="button"
						onclick="checkUpdate(form)" value="수정" class="제출">
				</div>
			</form>
			<script src="vali_updateUser.js"></script>
		</div>
	</section>
	<%
	}
	%>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/vali_updateUser.js"></script>

</body>
</html>