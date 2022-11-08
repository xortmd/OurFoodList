<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	session.invalidate();
	
	try {
		Thread.sleep(800); 
	} catch (Exception e) {  
		e.printStackTrace();
	}
	
	response.sendRedirect("index");
	%>
</body>
</html>