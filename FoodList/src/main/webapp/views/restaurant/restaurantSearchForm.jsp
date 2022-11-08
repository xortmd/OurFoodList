<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="resources/searchResult.js"></script>
	<title>가게목록</title>
	<link rel="stylesheet" href="resources/gage.css">
</head>
<jsp:include page="/views/header.jsp"/>
<body>
	<div class="f1"> 
	<div class="contents">
		<form >
			<select id="kind" name="kind" required>
				<option value="전체">전체</option>
				<option value="한식">한식</option>
				<option value="일식">일식</option>
				<option value="중식">중식</option>
				<option value="양식">양식</option>
				<option value="분식">분식</option>
				<option value="기타">기타</option>
			</select>
			<select id="area" name="area" required>
				<option value="전체">전체</option>
				<option value="서울">서울</option>
				<option value="경기">경기</option>
				<option value="강원">강원</option>
				<option value="충북">충북</option>
				<option value="충남">충남</option>
				<option value="경북">경북</option>
				<option value="경남">경남</option>
				<option value="전북">전북</option>
				<option value="전남">전남</option>
				<option value="제주">제주</option>
				<option value="인천">인천</option>
				<option value="대전">대전</option>
				<option value="대구">대구</option>
				<option value="울산">울산</option>
				<option value="부산">부산</option>
				<option value="광주">광주</option>
			</select>
			<select id="sort" name="sort" required>
				<option value="평점순">평점순</option>
				<option value="좋아요순">좋아요순</option>
				<option value="리뷰순">리뷰순</option>
			</select>
			<input type="button" onclick="printResult()" value="검색"></input>
		</form>
	</div>
	<div class="list_wrap">
        <table border="1">
			<thead class="title">
			</thead>
			<tbody class="container">
			</tbody>
		</table>
	</div>
	</div>
</body>
</html>