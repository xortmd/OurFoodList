/**
* 
*/
// 아이디 유효성 확인
let idOk = false;
let idLog = false;
let realPhone = "";
$('.id').focusout(function() {
	let id = $('.id').val();

	if (id == "") {
		console.log("id : " + id);
		$("#checkId").html("아이디를 입력하세요");
		$('#checkId').attr('color', 'red');
	} else {
		console.log("존재 아이디 확인ㄱㄱㄱ");
		$("#checkId").html("");
		// 중복검사 
		$.ajax({
			url: "Password",
			type: "post",
			data: { id: id },
		}).done(function(response) {
			console.log("response: ", response);
			const duplId = response.duplId;
			console.log("duplId : " + duplId);

			if (duplId === "true") {	// 사용 가능
				console.log("id: " + duplId);
				idOk = true;
				realPhone = response.realPhone;
				console.log("번호:" + realPhone);
			}


		})
	}
})



// 연락처 유효성 확인
let phoneOk = false;
$('.phone').focusout(function() {
	console.log("연락처에서 포커스아웃 발생");
	let phone = $('.phone').val();

	if (phone == "") {
		console.log("phone : " + phone);
		$("#checkPhone").html("전화번호를 입력하세요");
		$('#checkPhone').attr('color', 'red');
	} else {
		if (phone.length != 13) {
			console.log("길이 : " + phone.length);
			$("#checkPhone").html("입력한 전화번호를 확인하세요");
			$('#checkPhone').attr('color', 'red');
		} else if (phone.charAt(3) != '-' || phone.charAt(8) != '-') {
			console.log("3번째 : " + phone.charAt(3));
			console.log("8번째 : " + phone.charAt(8));
			$("#checkPhone").html("전화번호 형식에 맞지 않습니다.");
			$('#checkPhone').attr('color', 'red');
		} else {
			$("#checkPhone").html("");
			// 중복 검사
			$.ajax({
				url: "JoinCheck",
				type: "post",
				data: { phone: phone }
			}).done(e => {
				const duplPhone = e.duplPhone;
				console.log("duplPhone : " + duplPhone);
				if (duplPhone === "true") {
					phoneOk = true;
				}
			})
		}
	}
})

function checkInfo(form) {
	let id = $('.id').val();
	let phone = $('.phone').val();

	if (phone === realPhone && idOk) {
		alert("회원정보와 일치합니다.\n비밀번호 수정페이지로 이동합니다.");
		form.submit();
		console.log("삭제하러 간다잉");
	}
	else {
		if (id === "" && phone === "") {
			$("#checkPhone").html("정보를 입력하세요");
			$('#checkPhone').attr('color', 'red');
		} else {
			$("#checkPhone").html("정확한 입력입니다.");
			$('#checkPhone').attr('color', 'red');
		}
	}


}

let pwOk = false;
let check = false;
let password = "";
let passwordCheck = "";
$('.password').focusout(function() {
	password = $('.password').val();


	if (password.length < 5 || password.length > 15) {
		$("#checkPassword").html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPassword').attr('color', 'red');
		return;
	}

	substring = "!@#$%^&*()";
	for (let i = 0; i < substring.length; i++) {
		if (password.includes(substring.charAt(i))) {
			check = true;
		}
	}
	if (check) {
		$("#checkPassword").html("사용 가능한 비밀번호 입니다");
		$('#checkPassword').attr('color', 'green');
		pwOk = true;
	} else {
		$('#checkPassword').html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPassword').attr('color', 'red');
	}

})

$('.passwordCheck').focusout(function() {
	passwordCheck = $('.passwordCheck').val();
	console.log(passwordCheck);

	if (passwordCheck.length < 5 || passwordCheck.length > 15) {
		$("#checkPasswordCheck").html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPasswordCheck').attr('color', 'red');
		return;
	}
	console.log(passwordCheck);
	check = false;
	substring = "!@#$%^&*()";
	for (let i = 0; i < substring.length; i++) {
		if (passwordCheck.includes(substring.charAt(i))) {
			check = true;
		}
	}

	if (check) {
		$("#checkPasswordCheck").html("사용 가능한 비밀번호 입니다");
		$('#checkPasswordCheck').attr('color', 'green');
		pwOk = true;
	} else {
		$('#checkPasswordCheck').html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPasswordCheck').attr('color', 'red');
	}
})





let goToUpdate = false;
function updateInfo(form) {
	console.log("password:" + password);
	console.log("passwordCheck:" + passwordCheck);
	if (check && password === passwordCheck && pwOk) {
		console.log("수정하려갑니다.");
		form.submit();
	} else {

		if (!pwOk) {
			$('#checkPasswordCheck').html("비밀번호를 입력하세요.");
			$('#checkPasswordCheck').attr('color', 'red');
		} else {
			$('#checkPasswordCheck').html("비밀번호가 일치하지 않습니다.");
			$('#checkPasswordCheck').attr('color', 'red');

		}
	}
}
