<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/boardForm.css">
<title>공지사항</title>
</head>
<jsp:include page="/views/header.jsp"/>
<body>


<%
NoticeDao dao = NoticeDao.getInstance();
ArrayList<NoticeDto> list = dao.getNoticeAll();


String log = null;
if (session.getAttribute("log") != null) {
	log = (String) session.getAttribute("log");
}
	%>
<section class="notice">
  <div class="page-title">
        <div class="container">
            <h3>공지사항</h3>
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
                <%for(NoticeDto notice : list) {
                	if(notice.getHighlight() == 1){
                %>
                <tbody class="highlight_notice">
                <tr>
                    <th>
                     <a href="noticeViewForm?no=<%=notice.getNo()%>"><strong><%=notice.getTitle()%></strong></a>
                    </th>
                    <td><strong><%=notice.getUser_id()%></strong></td>
                    <td><strong><%=notice.getReg_date()%></strong></td>
                    <td><%=notice.getView_cnt()%></td>
                </tr>
                </tbody>
                <%} else{%>
                <tbody>
                   <tr>
                       <th>
                         <a href="noticeViewForm?no=<%=notice.getNo()%>"><%=notice.getTitle()%></a>
                       </th>
                       <td><%=notice.getUser_id()%></td>
                       <td><%=notice.getReg_date()%></td>
                       <td><%=notice.getView_cnt()%></td>
                     </tr>
                     </tbody>       
               <%} 
               }%>
            </table>
        </div>
    </div>

</section>

<% if(log != null) { 
	if(log.equals("realadmin")){
%>
<button onclick="location.href='noticeWriteForm'">글쓰기</button>	
<%}
	}%>
</body>
</html>