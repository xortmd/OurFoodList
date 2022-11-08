<%@page import="like.LikeDao"%>
<%@page import="restaurant.RestaurantDto"%>
<%@page import="restaurant.RestaurantDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta charset="UTF-8">
	<title>가게정보</title>
</head>
<body>
	<%
	RestaurantDao dao = RestaurantDao.getInstance();
	int code = Integer.parseInt(request.getParameter("code"));		
	RestaurantDto restaurant = dao.getRestaurantByCode(code);
	
	
	
	LikeDao likeDao = LikeDao.getInstance();
	
	String log = null;
	if(session.getAttribute("log") != null) {
		log = (String)session.getAttribute("log");
	}
	%>
	

	<ul>
		<li><img src=<%=restaurant.getImage_url()%>></li>
		<li>가게명: <%=restaurant.getRes_name()%></li>
		<li>가게주소: <%=restaurant.getAddress()%></li>
		<li>가게전화번호: <%=restaurant.getRes_phone()%></li>
		<li>음식종류: <%=restaurant.getKind()%></li>
		<li>등록일자: <%=restaurant.getReg_date()%></li>
		<li>평점: <%=restaurant.getAve_grade()%></li>
		<li>좋아요수: <%=restaurant.getLike_cnt()%></li>
		<li>리뷰수: <%=restaurant.getReview_cnt()%></li>
	</ul>
	
	
	
	<div>
		<form action="MyLikeCheckAction">
			<input type="hidden" id="resto_code" name="resto_code" value="<%=code%>">
			<%if(likeDao.myLikeExist(log, code) != -1) {%>
			<input type="image" src="https://i.pinimg.com/564x/c3/43/de/c343de84efd5abb30b9c9964a9b69492.jpg" >
			<%} else {%>
			<input type="image" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRnlDrQp31RbrhOeXM1ynrB40sosXaIeYfy0W19PpR-t7r_ghdvobhZHzzd95lX00MJs2E&usqp=CAU" >
			<%} %>
		</form>
	</div>
	
	
	
	<div>
		<div id="map" style="width: 350px;;height:350px;"></div>
		<jsp:include page="/views/review/reviewForm.jsp"/>
		<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	31e5ff35dbba96b3586d6f449bd66c12&libraries=services"></script>
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    		mapOption = {
        		center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        		level: 3 // 지도의 확대 레벨
    		};  

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 

			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();

			// 주소로 좌표를 검색합니다
			geocoder.addressSearch('<%=restaurant.getAddress()%>', function(result, status) {

    			// 정상적으로 검색이 완료됐으면 
    			if (status === kakao.maps.services.Status.OK) {

        			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        			// 결과값으로 받은 위치를 마커로 표시합니다
        			var marker = new kakao.maps.Marker({
            			map: map,
            			position: coords
        			});

        			// 인포윈도우로 장소에 대한 설명을 표시합니다
        			var infowindow = new kakao.maps.InfoWindow({
            			content: '<div style="width:150px;text-align:center;padding:6px 0;"><%=restaurant.getRes_name()%></div>'
        			});
        			infowindow.open(map, marker);

        			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        			map.setCenter(coords);
    			}
			});    
		</script>

	</div>
</body>
</html>