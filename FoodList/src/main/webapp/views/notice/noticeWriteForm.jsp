<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 글쓰기</title>
    <link rel="stylesheet" href="resources/board.css">
</head>
<jsp:include page="/views/header.jsp"/>
<body>
<%

String log = null;
if (session.getAttribute("log") != null) {
	log = (String) session.getAttribute("log");
}

%>

 <form class="write" action="NoticeWrite">
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
                            <dd><input type="text" placeholder="제목을 입력해주세요." name="title" required></dd>
                        </dl>
                    </div>
                    <div class="info">
                        <dl>
                            <dt>아이디</dt>
                            <dd><input type="text" style="border : none" name="user_id" value="<%=log%>" readonly></dd>
                        </dl>
                    </div>
                    <div class="cont">
                        <input type="text" placeholder="내용을 입력해주세요." class="text" name="content" required>
                    </div>
                </div>
                <div class="bt_wrap">
                	<select name="highlight" id="highlight">
            		<option value="0">기본 공지 설정</option>
            		<option value="1">중요 공지 설정</option>
            	</select>
                    <input type="submit" value="글쓰기" onclick="alert('글작성 완료')"> 
                    <input type="button" onclick="location.href='noticeForm'" value="돌아가기">
                </div>
            </div>
        </div>
    </form>

</body>
</html>