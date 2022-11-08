<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
NoticeDao dao = NoticeDao.getInstance();

request.setCharacterEncoding("utf-8");
int no = Integer.parseInt(request.getParameter("no"));
System.out.println("no" + no);

NoticeDto notice = dao.getNoticeByNo(no); 


%>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/boardWriteForm.css">
<title><%=notice.getTitle()%></title>
</head>
<body>


    <header>
        <div class="homebox">
            <div>
                <a href="#" class="logo"></a>
            </div>
        </div>
    </header>

    <main>
        <form class="write" method="post" action="NoticeUpdate">
         	<input type="hidden" name="no" value="<%=no%>">
        	<input type="text" value="<%=notice.getUser_id() %>" readonly>
            <input type="text" placeholder="제목을 입력해주세요." value="<%=notice.getTitle() %>" id="title" name="title" >
            <hr>
            <input type="text" placeholder="내용을 입력해주세요." class="text" value="<%=notice.getContent() %>" id="content" name="content" >
            <div class="button">
            	<select name="highlight" id="highlight">
            		<option value="0">기본 공지 설정</option>
            		<option value="1">중요 공지 설정</option>
            	</select>
                <input type="submit" onclick="alert('글수정 완료')"  value="수정하기" > 
            </div>
        </form>
    </main>

</body>
</html>