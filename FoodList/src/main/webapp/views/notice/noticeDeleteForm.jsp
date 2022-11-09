<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="resources/boardDelete.css">
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
	<div class="btn">
	<input type="submit" value="예" name="true">
	<button type="button" onclick="location.href='noticeForm?no=<%=notice.getNo()%>'">아니요</button>
	</div>
	</form>
</body>

</html>