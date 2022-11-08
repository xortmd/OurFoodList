<%@page import="com.mysql.cj.Session"%>
<%@page import="board.BoardDto"%>
<%@page import="board.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
BoardDao dao = BoardDao.getInstance();
BoardDto board = null;

request.setCharacterEncoding("utf-8");
// System.out.println("결과 : " + request.getParameter("no"));
int no = Integer.parseInt(request.getParameter("no"));
System.out.println("no" + no);

board = dao.getBoardByNo(no); 
dao.addViewCnt(no);


%>

<meta charset="UTF-8">
<link rel="stylesheet" href="resources/board.css">
<title><%=board.getTitle()%></title>
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
                <strong>커뮤니티</strong>
                <p>회원님의 진심을 알고 싶습니다.</p>
            </div>
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" value="<%=board.getTitle() %>" id="title" name="title" readonly></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>아이디</dt>
                            <dd><input type="text" id="id" value="<%=board.getUser_id() %>" placeholder="" readonly></dd>
                        </dl>
                        <dl>
                            <dt>작성일자</dt>
                            <dd><input type="text" value="<%=board.getReg_date()%>" > 
                                <input type="hidden" name="no" value="<%=board.getNo()%>">
                            </dd>
                        </dl>
                    </div>
                    <div class="cont">
                       <input type="text" class="text" value="<%=board.getContent() %>" id="content" name="content" readonly>
                    </div>
                </div>
                <div class="bt_wrap">
                   <%if(log  != null){ %>
                   <%if(log.equals(board.getUser_id())) { %> 
                <input type="button" onclick="location.href='boardUpdateForm?no=<%=board.getNo()%>'" value="글수정">         
                <input type="button" onclick="location.href='boardForm'" value="돌아가기">
                <input type="button"  value="글삭제" onclick="location.href='boardDeleteForm?no=<%=board.getNo()%>'" name="delete" >
               <%}
                   }%>  
            </div>
                </div>
            </div>

    </form>
</body>
</html>