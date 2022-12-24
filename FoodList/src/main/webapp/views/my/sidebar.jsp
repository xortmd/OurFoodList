<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>반응형 사이드바 메뉴</title>
	<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:100,300,400,500,700,900&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" href="resources/sidebar.css">
</head>
<body> 
<div class="menu">
   <!-- <label for="expand-menu"><div>메뉴</div></label><input type="checkbox" id="expand-menu" name="expand-menu"> -->
    <ul>
        <li><a href="myPageForm" class="item"><div>프로필</div></a></li>
        <li><a href="myBoard" class="item"><div>My Board</div></a></li>
        <li><a href="myReview" class="item"><div>My Review</div></a></li>
        <li><a href="myLike" class="item"><div>My Like</div></a></li>
<!--         <li><a href="#" class="item"><div>설정</div></a></li> -->
        <li><a href="myDeleteForm" class="item"><div>회원탈퇴</div></a></li>
    </ul>
</div>
    
</body>
</html>