/**
 * 
 */


function checkInfo(form) {
	let password = $('.delete_password').val();
	let user_pw = $('.user_pw').val();

	if (password == "") {
		$('#checkInfo').html("탈퇴시 비밀번호 입력은 필수입니다");
		$('#checkInfo').attr("color", "red");
	} else {
		if(user_pw === password){
			alert("탈퇴가 완료되었습니다");
			form.submit();
		}else{
			$('#checkInfo').html("비밀번호가 일치하지 않습니다");
			$('#checkInfo').attr("color", "red");	
		}
	}
}
