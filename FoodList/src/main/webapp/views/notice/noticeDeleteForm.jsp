<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
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
NoticeDao dao = NoticeDao.getInstance();
int no = Integer.parseInt(request.getParameter("no"));

NoticeDto notice = dao.getNoticeByNo(no);


%>




	<h3><%=notice.getUser_id()%>님 "<%=notice.getTitle() %>" 글을 삭제하시겠습니까</h3>
	
	<form method="POST" action="NoticeDelete">
	<input type="hidden" name="no" value="<%=notice.getNo()%>">
	<input type="submit" value="예" name="true">
	</form>
	<button type="button" onclick="location.href='noticeViewForm?no=<%=notice.getNo()%>'">아니요</button>
	
</body>
</html>