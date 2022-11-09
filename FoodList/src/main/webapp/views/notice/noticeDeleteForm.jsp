<%@page import="notice.NoticeDto"%>
<%@page import="notice.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="resources/boardDelete.css">
<title>글 삭제</title>
</head>


<body>
<%
NoticeDao dao = NoticeDao.getInstance();
int no = Integer.parseInt(request.getParameter("no"));

NoticeDto notice = dao.getNoticeByNo(no);


%>




	<h3><%=notice.getUser_id()%>님 "<%=notice.getTitle() %>" 글을 삭제하시겠습니까</h3>
	
	<form method="POST" action="NoticeDelete">
	<div class="btn">
	<input type="hidden" name="no" value="<%=notice.getNo()%>">
	<input type="submit" value="예" name="true">
	</form>
	<button type="button" onclick="noticeForm">아니요</button>
	</div>
</body>

<script src = "js/jquery-3.6.0.min.js"></script>
<script>
$('.input_id').focusout(function(){
	let userId = $('.input_id').val(); // input_id에 입력되는 값
	
	$.ajax({
		url : "IdCheckService",
		type : "post",
		data : {userId: userId},
		dataType : 'json',
		success : function(result){
			if(result == 0){
				$("#checkId").html('사용할 수 없는 아이디입니다.');
				$("#checkId").attr('color','red');
			} else{
				$("#checkId").html('사용할 수 있는 아이디입니다.');
				$("#checkId").attr('color','green');
			} 
		},
		error : function(){
			alert("서버요청실패");
		}
	})
	 
})

</script>

</html>