<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
<%@page import="com.mysql.cj.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
NoticeDao dao = NoticeDao.getInstance();
NoticeDto notice = null;

request.setCharacterEncoding("utf-8");
int no = Integer.parseInt(request.getParameter("no"));

notice = dao.getNoticeByNo(no);
dao.addViewCnt(no);


%>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/board.css">
<title><%=notice.getTitle()%></title>
</head>
<jsp:include page="/views/header.jsp"/>
<body>
<%
String log = null;
if(session.getAttribute("log") != null){
	log = (String)session.getAttribute("log");
}

%>
    <form class="write" method="post" >
        <div class="board_wrap">
            <div class="board_title">
                <strong>공지사항</strong>
                <p>회원님의 진심을 알고 싶습니다.</p>
            </div>
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" placeholder="제목을 입력해주세요." value="<%=notice.getTitle() %>" id="title" name="title" readonly></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>아이디</dt>
                            <dd><input type="text" id="id" name="user_id" value="<%=notice.getUser_id() %>" placeholder="" readonly></dd>
                        </dl>
                        <dl>
                            <dt>작성일자</dt>
                            <dd><input type="text" value="<%=notice.getReg_date()%>" > 
-                                <input type="hidden" name="no" value="<%=notice.getNo()%>">
                            </dd>
                        </dl>
                    </div>
                    <div class="cont">
                       <input type="text" placeholder="내용을 입력해주세요." class="text" value="<%=notice.getContent() %>" id="content" name="content" readonly>
                    </div>
                </div>
                <div class="bt_wrap">
                   <%if(log  != null){ %>
                   <%if(log.equals("realadmin")) { %> 
                <input type="button" onclick="location.href='noticeUpdateForm?no=<%=notice.getNo()%>'" value="글수정">         
                <input type="button" onclick="location.href='noticeForm'" value="돌아가기">
                <input type="button"  value="글삭제" onclick="location.href='noticeDeleteForm?no=<%=notice.getNo()%>'" name="delete" >
               <%} else{%>
                   <input type="button" onclick="location.href='noticeForm'" value="돌아가기">

                  <% }
                   }%>  
            </div>
                </div>
            </div>

    </form>
</body>
</html>