<%@page import="restaurant.RestaurantDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>맛집등록</title>
<link rel="stylesheet" href="resources/restaurantCreate.css">
</head>
<jsp:include page="/views/header.jsp"/>
<body>
	<%
	RestaurantDao dao = RestaurantDao.getInstance();
	int code = dao.codeGenerator();
	%>
	<section class="res_create">
		<div class="form-container">
			<h2 style="margin-bottom: 20px">맛집 등록</h2>
			<form method="POST" action="RestaurantCreate" class="form">
				<div class="code">
					<label>가게코드(자동발생)</label> <input type="text" id="code" name="code"
						value="<%=code%>" readonly><br>
				</div>
				<div class="res_name" style="margin-top: 15px">
					<label>가게명</label> <input type="text" id="res_name" name="res_name"
						placeholder="가게명을 입력하세요." autofocus required><br>
				</div>
				<div class="address" style="margin-top: 15px">
					<label>주소</label> <input type="text" id="address" name="address"
						placeholder="주소를 입력하세요." required><br>
				</div>
				<div class="res_phone" style="margin-top: 15px">
					<label>전화번호</label> <input type="text" id="res_phone"
						name="res_phone" placeholder="전화번호를 입력하세요." required><br>
				</div>
				<div class="kind" style="margin-top: 15px">
					<label>음식종류</label> <select id="kind" name="kind" required>
						<option value="전체">전체</option>
						<option value="한식">한식</option>
						<option value="일식">일식</option>
						<option value="중식">중식</option>
						<option value="양식">양식</option>
						<option value="분식">분식</option>
						<option value="기타">기타</option>
					</select>
				</div>
				<div class="image_url" style="margin-top: 15px">
					<label>이미지</label> <input type="text" id="image_url"
						name="image_url" placeholder="이미지 주소를 복사해주세요" required><br>
				</div>
				<input class="btn" type="submit" value="등록">
			</form>
		</div>
	</section>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
  window.onload = function () {
    document.getElementById("address").addEventListener("click", function () { //주소입력칸을 클릭하면
      //카카오 지도 발생
      new daum.Postcode({
        oncomplete: function (data) { //선택시 입력값 세팅
          document.getElementById("address").value = data.address; // 주소 넣기
        }
      }).open();
    });
  }
</script>
</html>