<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="resources/login.css">
</head>
	<jsp:include page="/views/header.jsp"/>
<body>
<%-- 	<jsp:include page="header.jsp"/> --%> 
    <section class="login-form">
        <h1>LOGIN</h1> 
        <form method="post" action="Login" class="form">
<!--         	<input type="hidden" name="command" value="Login"> -->
            <div class="int-area">
                <input type="text" name="id" class="id" id="id" autocomplete="off" required>
                <label for="id"> User name</label>
            </div>
            <div class="int-area">
                <input type="text" name="password" class="password" id="pw" autocomplete="off" required>
                <label for="id"> password</label>
            </div>
            <font id="checkLog" size=2></font> 
            <div class="btn-area">
            	<input class="button" type="button" onclick="checkLogin(form)" value="LOGIN" style="cursor:pointer;">	<!-- 로그인 버튼 꾸며야 함 -->
<!--                 <button type="submit">LOGIN</button> -->
            </div>
        </form>
        <div class="caption">
        	<a href="idForm">forgot id?	|  </a>  
            <a href="passwordForm">forgot password?</a>
        </div>
        <script src="validation.js"></script>
    </section>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script src="resources/validation.js"></script>

</body>
</html>