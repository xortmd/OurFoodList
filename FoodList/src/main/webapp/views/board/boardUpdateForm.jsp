<%@page import="board.BoardDto"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
BoardDao dao = BoardDao.getInstance();

request.setCharacterEncoding("utf-8");
int no = Integer.parseInt(request.getParameter("no"));
System.out.println("no" + no);

BoardDto board = dao.getBoardByNo(no); 


%>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/boardWriteForm.css">
<title><%=board.getTitle()%></title>
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
        <form class="write" method="post" action="BoardUpdate">
         	<input type="hidden" name="no" value="<%=no%>">
        	<input type="text" value="<%=board.getUser_id() %>" readonly>
            <input type="text" placeholder="제목을 입력해주세요." value="<%=board.getTitle() %>" id="title" name="title" >
            <hr>
            <input type="text" placeholder="내용을 입력해주세요." class="text" value="<%=board.getContent() %>" id="content" name="content" >
            <div class="button">
                <input type="submit" onclick="alert('글수정 완료')"  value="수정하기" > 
            </div>
        </form>
    </main>

</body>
</html>