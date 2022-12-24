/**
 * 
 */
let pwOriOk = false;
let password = $('.password').val();
let id = $('.id').val();
let birth = $('.birth').val();

$('.passwordOri').focusout(function() {
	let passwordOri = $('.passwordOri').val();

	if (password === passwordOri) {
		pwOriOk = true;
		$('#checkPasswordOri').html("기존 비밀번호와 일치합니다.");
		$('#checkPasswordOri').attr("color", "green");
	} else if (passwordOri != "" && password != passwordOri) {
		$('#checkPasswordOri').html("기존 비밀번호와 일치하지 않습니다.");
		$('#checkPasswordOri').attr("color", "red");
	}
})


let check = false;
$('.passwordNew').focusout(function() {
	let passwordNew = $('.passwordNew').val();

	if (passwordNew.length < 5 || passwordNew.length > 15) {
		$("#checkPasswordNew").html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPasswordNew').attr('color', 'red');
		return;
	}

	substring = "!@#$%^&*()";
	for (let i = 0; i < substring.length; i++) {
		if (password.includes(substring.charAt(i))) {
			check = true;
		}
	}

	if (check) {
		$("#checkPasswordNew").html("사용 가능한 비밀번호 입니다");
		$('#checkPasswordNew').attr('color', 'green');
		pwOk = true;
	} else {
		$('#checkPasswordNew').html("5~15자 영문 대 소문자, 특수문자!@#$%^&*()를 사용하세요.");
		$('#checkPasswordNew').attr('color', 'red');
	}
})





let goToUpdate = false;
$('.passwordNewCheck').focusout(function() {
	let passwordNew = $('.passwordNew').val();
	let passwordNewCheck = $('.passwordNewCheck').val();

	if (passwordNew == "") {
		if (passwordNewCheck != "") {
			console.log("새 비밀번호 확인: " + passwordNewCheck);
			$('#checkPasswordNew').html("비밀번호 수정 시 필수 값입니다");
			$('#checkPasswordNew').attr('color', 'red');
		}

	} else {
		if (passwordNewCheck == "") {
			$('#checkPasswordNewCheck').html("비밀번호 수정 시 필수 값입니다");
			$('#checkPasswordNewCheck').attr('color', 'red');
		} else {
			// 새 비밀번호 두개 동일한지
			if (passwordNew === passwordNewCheck) {
				$('#checkPasswordNewCheck').html("변경된 비밀번호와 일치합니다");
				$('#checkPasswordNewCheck').attr('color', 'green');

				// 여기서 어떻게 변경해준다
				if (check) {
					if (pwOriOk) {
						goToUpdate = true;
					} else {
						$('#checkPasswordOri').html("비밀번호 수정 시 필수 값입니다");
						$('#checkPasswordOri').attr('color', 'red');
					}
				} 
			} else {
				$('#checkPasswordNewCheck').html("변경된 비밀번호와 일치하지 않습니다.");
				$('#checkPasswordNewCheck').attr('color', 'red');
			}
		}
	}
})


let phoneOk = false;
$('.phoneNew').focusout(function() {
	let phone = $('.phoneNew').val();

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
					phoneOk = true;
					$('#checkPhone').html('사용 가능한 연락처입니다');
					$('#checkPhone').attr('color', 'green');
				} else {						// 중복
					if(phone === $('.phoneOri').val()){
						phoneOk = true;
						$('#checkPhone').html('');
					}else{
						$('#checkPhone').html('이미 등록된 연락처입니다');
						$('#checkPhone').attr('color', 'red');
					}
				}
			})
		}
	}
})



function checkUpdate(form){
	let id = $('.idCont').val();
	if ($('.passwordNew').val() != "") {
		password = $('.passwordNew').val();	
	}else{
		password = $('.password').val();
	}
	let name = $('.nameNew').val();
	let phone = $('.phoneNew').val();
	
	if(!(password === $('.password').val() && goToUpdate) && phoneOk){
		alert("수정이 완료되었습니다.");
		form.submit();
	}	
}