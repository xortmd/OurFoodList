// 아이디 유효성 확인
let idOk = false;
let idLog = false;
$('.id').focusout(function() {
	let id = $('.id').val();

	if (id == "") {
		$("#checkId").html("아이디를 입력하세요");
		$('#checkId').attr('color', 'red');
	} else {		
		// 중복검사 
		$.ajax({
			url: "JoinCheck",
			type: "post",
			data: { id: id },
		}).done(function(response) {
			const duplId = response.duplId;

			if (duplId === "false") {	// 사용 가능
				idOk = true;
				$("#checkId").html('사용 가능한 아이디입니다.');
				$('#checkId').attr('color', 'green');
			} else {			// 중복 존재
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
	let phone = $('.phone').val();

	if (phone == "") {
		$("#checkPhone").html("전화번호를 입력하세요");
		$('#checkPhone').attr('color', 'red');
	} else {
		if (phone.length != 13) {
			$("#checkPhone").html("입력한 전화번호를 확인하세요");
			$('#checkPhone').attr('color', 'red');
		} else if (phone.charAt(3) != '-' || phone.charAt(8) != '-') {
			$("#checkPhone").html("전화번호 형식에 맞지 않습니다.");
			$('#checkPhone').attr('color', 'red');
		} else {
			// 중복 검사
			$.ajax({
				url: "JoinCheck",
				type: "post",
				data: { phone: phone }		
			}).done(e => {
				const duplPhone = e.duplPhone;
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
	let birthString = $('.birth').val();
	let birth = new Date($('.birth').val());
	console.log("birth : " + birth);
	console.log("birthString : " + birthString);
	const dateNow = new Date();
	console.log("오늘 날짜 : " + dateNow.toString());

	if (birthString == "") {
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


function checkJoin(form) {
	if (idOk && pwOk && nameOk && phoneOk && birthOk) {
		alert("회원가입이 성공적으로 완료되었습니다.\n로그인 페이지로 이동합니다");
		form.submit();
	}
}


function checkLogin(form) {
	let id = $('.id').val();
	let password = $('.password').val();

	if (idLog) {
		$.ajax({
			url: "LoginCheck",
			type: "post",
			data: { id: id, password: password },				
			dataType: "json",
		}).done(e => {
			const ok = e.ok;
			if (!ok) {						// 중복
				$('#checkLog').html('로그인 정보를 확인하세요');
				$('#checkLog').attr('color', 'red');
			} else {
				if(password === "****"){
					$('#checkLog').html('로그인 정보를 확인하세요');
					$('#checkLog').attr('color', 'red');		
				}else{
					form.submit();					
				}
			}
		})
	} else {
		$('#checkLog').html('로그인 정보를 확인하세요');
		$('#checkLog').attr('color', 'red');
	}
}

