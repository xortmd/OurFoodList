<%@page import="board.BoardDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/boardForm.css">
<title>커뮤니티</title>
</head>
<jsp:include page="/views/header.jsp"/>
<body>


<%
BoardDao dao = BoardDao.getInstance();
ArrayList<BoardDto> list = dao.getBoardAll();

String log = null;
if (session.getAttribute("log") != null) {
	log = (String) session.getAttribute("log");
}
	%>
<section class="notice">
  <div class="page-title">
        <div class="container">
            <h3>커뮤니티</h3>
        </div>
    </div>
  <!-- board list area -->
    <div id="board-list">
        <div class="container">
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-userid">아이디</th>
                    <th scope="col" class="th-date">등록일</th>
                    <th scope='col' class="th-view">조회수</th>
                </tr>
                </thead>
                <%for(BoardDto board : list) {%>
                <tbody>
                <tr>
                    <th>
                      <a href="boardViewForm?no=<%=board.getNo()%>"><%=board.getTitle()%></a>
                    </th>
                    <td><%=board.getUser_id() %></td>
                    <td><%=board.getReg_date()%></td>
                    <td><%=board.getView_cnt() %></td>
                </tr>
                </tbody>
                <%} %>
            </table>
        </div>
    </div>

</section>

<% if(log != null) { %>
<button onclick="location.href='boardWriteForm'">글쓰기</button>
<% }%>
</body>
</html>