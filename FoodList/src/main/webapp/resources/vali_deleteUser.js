/**
 * 
 */
//$('.delete_password').focusout(function() {
//	console.log("포커스 아웃이 발생했느냐");
//	const id = $('.delete_id').val();
//	let password = $('.delete_password').val();
//
//	console.log("id : " + id);
//	console.log("password : " + password);
//
//	if (password == "") {
//		console.log("비밀번호 비었음");
//		$('#checkInfo').html("탈퇴시 비밀번호 입력은 필수입니다");
//		$('#checkInfo').attr("color", "red");
//	} else {
//		// ajax로 비밀번호 확인
//	}
//})

function checkInfo(form) {
//	const id = $('.delete_id').val();
	let password = $('.delete_password').val();
	let user_pw = $('.user_pw').val();
	console.log("user_pw : " + user_pw);

	if (password == "") {
		console.log("비밀번호 비었음");
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
