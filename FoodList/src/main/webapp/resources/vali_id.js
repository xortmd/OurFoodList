/**
* 
*/
// 아이디 유효성 확인
let NameOk = false;
let realPhone = "";
let name = "";
let id = "";
$('.name').focusout(function() {
	name = $('.name').val();
	console.log("이름 : " + name);

	if (name == "") {
		console.log("name : " + name);
		$("#checkName").html("이름을 입력하세요");
		$('#checkName').attr('color', 'red');
	} else {
		$("#checkName").html("");
	}
})


// 연락처 유효성 확인
let phoneOk = false;
let realName = "";
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
				url: "PhoneCheck",
				type: "post",
				data: { phone: phone }
			}).done(function(response) {
				console.log("response: ", response);
				id = response.id;
				realName = response.realName;
				console.log("id : " + id);
				console.log("realName : " + realName);
				if(name === realName){
					NameOk = true;
				}
			})
		}
	}
})

function checkInfo(form) {
//	let phone = $('.phone').val();

	if (NameOk) {
		$("#checkPhone").html("고객님의 아이디는 " + id + "입니다");
		$('#checkPhone').attr('color', 'green');
		console.log("일치함");
		$('.button *').remove();
		$('.button').append(
			`<button class="search-btn-wrap" style="margin-top:25px" onclick="location.href='login'">로그인 하러가기</button>`
		);
	}
	else {
		$("#checkPhone").html("회원정보와 일치하지 않습니다.");
		$('#checkPhone').attr('color', 'red');
		
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