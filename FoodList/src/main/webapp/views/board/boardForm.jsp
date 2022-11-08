<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
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
NoticeDao Ndao = NoticeDao.getInstance();
ArrayList<NoticeDto> nlist = Ndao.getNoticeAll();

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
        <p>전체 게시글 수 : <%=dao.getTotalCountOnBoard()%></p>
            <table class="board-table">
                <thead>
                <tr>
                    <th scope="col" class="th-title">제목</th>
                    <th scope="col" class="th-userid">아이디</th>
                    <th scope="col" class="th-date">등록일</th>
                    <th scope='col' class="th-view">조회수</th>
                </tr>
                </thead>
                
                <tbody>
                <%for(NoticeDto notice : nlist){
                	if(notice.getHighlight() == 1) {%>
                	<tr>
                    <th class="noticeInBoard">
                      <a href="noticeViewForm?no=<%=notice.getNo()%>" style="color : red">[중요] <%=notice.getTitle()%></a>
                    </th>
                    <td><strong><%=notice.getUser_id() %></strong></td>
                    <td><strong><%=String.valueOf(notice.getReg_date()).split(" ")[0]%></strong></td>
                    <td><strong><%=notice.getView_cnt() %></strong></td>
                </tr> 	
                <% }
                }%>
                <%for(BoardDto board : list) {%>
                <tr>
                    <th>
                      <a href="boardViewForm?no=<%=board.getNo()%>"><%=board.getTitle()%></a>
                    </th>
                    <td><%=board.getUser_id() %></td>
                    <td><%=String.valueOf(board.getReg_date()).split(" ")[0]%></td>
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