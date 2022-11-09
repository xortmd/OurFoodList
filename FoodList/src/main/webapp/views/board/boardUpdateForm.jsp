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
<link rel="stylesheet" href="resources/board.css">
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
    

    <form class="write" action="BoardUpdate">
        <div class="board_wrap">
            <div class="board_title">
                <strong>커뮤니티</strong>
                <p>회원님의 진심을 알고 싶습니다.</p>
            </div>
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" placeholder="제목을 입력해주세요." value="<%=board.getTitle() %>" name="title" required></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>아이디</dt>
                            <dd><input type="text" name="user_id" style="border: none; outline :none" value="<%=board.getUser_id()%>" readonly></dd>
                        </dl>

                    </div>
                    <div class="cont">
                        <input type="text" placeholder="내용을 입력해주세요." value="<%=board.getContent() %>" class="text" name="content" required>
                        <input type="hidden" name="no" value="<%=no%>">
                    </div>
                </div>
                <div class="bt_wrap">
                    <input type="submit" value="수정하기" onclick="alert('글수정 완료')"> 
                    <input type="button" onclick="location.href='boardForm'" value="돌아가기">
                </div>
            </div>
        </div>
    </form>

</body>
</html>