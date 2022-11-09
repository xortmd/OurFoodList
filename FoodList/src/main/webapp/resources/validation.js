/**
* 
*/
// 아이디 유효성 확인
let idOk = false;
let idLog = false;
$('.id').focusout(function() {
	console.log("아이디에서 포커스아웃 발생");
	console.log("되는거 맞니?");
	let id = $('.id').val();

	if (id == "") {
		console.log("id : " + id);
		$("#checkId").html("아이디를 입력하세요");
		$('#checkId').attr('color', 'red');
	} else {		
		console.log("중복 검사 ㄱㄱ");
		// 중복검사 
		$.ajax({
			url: "JoinCheck",
			type: "post",
			data: { id: id },
		}).done(function(response) {
			console.log("response: ", response);
			const duplId = response.duplId;
			console.log("duplId : " + duplId);

			if (duplId === "false") {	// 사용 가능
				console.log("사용가능");
				idOk = true;
				$("#checkId").html('사용 가능한 아이디입니다.');
				$('#checkId').attr('color', 'green');
			} else {			// 중복 존재
				console.log("사용불가");
				$("#checkId").html('사용할 수 없는 아이디입니다.');
				$('#checkId').attr('color', 'red');
				idLog = true;
			}

		})
	}
})

// 비밀번호 유효성 확인
let pwOk = false;
$('.password').focusout(function() {
	console.log("패스워드에서 포커스아웃 발생");
	let password = $('.password').val();

	if (password == "") {
		console.log("password : " + password);
		$("#checkPassword").html("비밀번호를 입력하세요");
		$('#checkPassword').attr('color', 'red');
	} else {
		// 양식 검사
		console.log("password : " + password);

		if (password.length < 5 || password.length > 15) {
			$("#checkPassword").html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
			$('#checkPassword').attr('color', 'red');
			return;
		}

		substring = "!@#$%^&*()";
		let check = false;
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
	}
})

// 이름 입력 확인
let nameOk = false;
$('.name').focusout(function() {
	console.log("이름에서 포커스아웃 발생");
	let name = $('.name').val();

	if (name == "") {
		console.log("name : " + name);
		$("#checkName").html("이름을 입력하세요");
		$('#checkName').attr('color', 'red');
	} else {
		$("#checkName").html("");
		nameOk = true;
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
			// 중복 검사
			console.log("js에서의 핸드폰 번호 : " + phone);
			$.ajax({
				url: "JoinCheck",
				type: "post",
				data: { phone: phone }		// , 붙이나? 안 붙여도 되네
			}).done(e => {
				const duplPhone = e.duplPhone;
				console.log("duplPhone : " + duplPhone);
				if (duplPhone === "false") {	// 사용 가능
					$('#checkPhone').html('사용 가능한 연락처입니다');
					$('#checkPhone').attr('color', 'green');
					phoneOk = true;
				} else {						// 중복
					$('#checkPhone').html('이미 등록된 연락처입니다');
					$('#checkPhone').attr('color', 'red');
				}
			})
		}
	}
})

let birthOk = false;
$('.birth').focusout(function() {
	console.log("생년월일에서 포커스아웃 발생");
	let birth = new Date($('.birth').val());
	console.log("birth : " + birth);
	const dateNow = new Date();
	console.log("오늘 날짜 : " + dateNow.toString());

	if (birth == "") {
		$("#checkBirth").html("생년월일을 입력하세요");
		$('#checkBirth').attr('color', 'red');
	} else {
		//  유효하지 않은 날짜 입력시
		if (dateNow - birth <= 0) {
			$("#checkBirth").html("유효하지 않은 날짜입니다");
			$('#checkBirth').attr('color', 'red');
		}else{
			console.log(dateNow - birth);
			$("#checkBirth").html("");
			birthOk = true;			
		}
	}
})

//function chkCharCode(event) {
//	const keyCode = event.keyCode;
//	console.log("keyCode : " + keyCode);
//	const isValidKey = (
//		(keyCode >= 48 && keyCode <= 57) || // Numbers
//		(keyCode >= 97 && keyCode <= 122) || // Numbers, Keypad
//		(keyCode >= 65 && keyCode <= 90) || // Alphabet
//		(keyCode === 32) || // Space
//		(keyCode === 8) || // BackSpace
//		(keyCode === 189) // Dash
//	);
//	if (!isValidKey) {
//		event.preventDefault();
//		return false;
//	}
//};

function checkJoin(form) {
	if (idOk && pwOk && nameOk && phoneOk && birthOk) {
		console.log("NEXT LEVEL");
		alert("회원가입이 성공적으로 완료되었습니다.\n로그인 페이지로 이동합니다");
		form.submit();
	} else {
		console.log("id : " + idOk);
		console.log("pw : " + pwOk);
		console.log("name : " + nameOk);
		console.log("phone : " + phoneOk);
		console.log("birth : " + birthOk);
		console.log("당신은 우리와 함께 갈 수 없습니다.");
	}
}


function checkLogin(form) {
	let id = $('.id').val();
	let password = $('.password').val();

	if (idLog) {
		$.ajax({
			url: "LoginCheck",
			type: "post",
			data: { id: id, password: password },				// , 붙이나? 안 붙여도 되네
			dataType: "json",
		}).done(e => {
			console.log("e : " + e);
			const ok = e.ok;
			console.log("ok : " + e.ok);
			if (!ok) {						// 중복
				$('#checkLog').html('로그인 정보를 확인하세요');
				$('#checkLog').attr('color', 'red');
			} else {
				form.submit();
			}
		})
	} else {
		$('#checkLog').html('로그인 정보를 확인하세요');
		$('#checkLog').attr('color', 'red');
	}
}

