/**
 * 
 */
$('.title').empty();
$('.container').empty();

console.log("이제 곧 리뷰 목록 찍을거야");

let id = $('.id').val();
console.log("id : " + id);

$.ajax({
	url: "Review",
	method: "post",
	data: { id: id }
}).done(function(response) {
	const list = JSON.parse(response);

	console.log("list : " + list);

	if (list != null) {
		$('.title').append(
			`<tr>
				<th>가게이름</th>
				<th>코멘트</th>
				<th>등록일</th>
				<th>평점</th>
		</tr>`
		)

		list.forEach(e => {
			const no = e.no;
			const resto_code = e.resto_code;
			const res_name = e.user_id;		// 아이디는 굳이 필요 없으니까 여기에 레스토랑 이름 담아가지고 옴
			const comment = e.coment;
			const reg_date = e.reg_date.split(" ")[0];
			const give_grade = e.give_grade;


			$('.container').append(
				`<tr>
                <td><a href="restaurantViewForm?resto_code=${resto_code}">${res_name}</a></td>
                <td>${comment}</td>
                <td>${reg_date}</td>
                <td>${give_grade}</td>
            </tr>`
			);
		});
	} else {
		$('.title').html("아직 작성한 리뷰가 없습니다:(");
	}

})