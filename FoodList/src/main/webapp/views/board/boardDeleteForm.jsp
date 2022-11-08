<%@page import="board.BoardDto"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>글 삭제</title>
</head>

	
<body>
<%
BoardDao dao = BoardDao.getInstance();
int no = Integer.parseInt(request.getParameter("no"));

BoardDto board = dao.getBoardByNo(no);


%>




	<h3><%=board.getUser_id()%>님 "<%=board.getTitle() %>" 글을 삭제하시겠습니까</h3>
	
	<form method="POST" action="BoardDelete">
	<input type="hidden" name="no" value="<%=board.getNo()%>">
	<input type="submit" value="예" name="true">
	</form>
	<button type="button" onclick="location.href='boardViewForm?no=<%=board.getNo()%>'">아니요</button>
	
</body>
</html>