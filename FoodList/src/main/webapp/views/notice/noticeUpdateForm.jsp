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
<link rel="stylesheet" href="resources/board.css">
<title><%=notice.getTitle()%></title>
</head>
<jsp:include page="/views/header.jsp"/>
<body>

<form class="write" action="NoticeUpdate">
        <div class="board_wrap">
            <div class="board_title">
                <strong>공지사항</strong>
                <p>관리자의 진심을 알려드리겠습니다.</p>
            </div>
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" placeholder="제목을 입력해주세요." value="<%=notice.getTitle()%>" name="title" required></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>아이디</dt>
                            <dd><input type="text" name="user_id" style="border: none; outline :none" value="<%=notice.getUser_id()%>" readonly></dd>
                        </dl>
                        <dl>
                            <dt>작성일자</dt>
                            <dd><input type="datetime" name="reg_date"  style="border: none; outline :none" value="<%=notice.getReg_date()%>" readonly></dd>
                        </dl>
                    </div>
                    <div class="cont">
                        <input type="text" placeholder="내용을 입력해주세요." value="<%=notice.getContent()%>" class="text" name="content" required>
         				<input type="hidden" name="no" value="<%=notice.getNo()%>">
                    </div>
                </div>
                <div class="bt_wrap">
                	<select name="highlight" id="highlight">
            		<option value="0">기본 공지 설정</option>
            		<option value="1">중요 공지 설정</option>
            	</select>
                    <input type="submit" value="수정하기" onclick="alert('글수정 완료')"> 
                    <input type="button" onclick="location.href='noticeForm'" value="돌아가기">
                </div>
            </div>
        </div>
    </form>



</body>
</html>