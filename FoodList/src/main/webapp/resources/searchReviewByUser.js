/**
 * 
 */
$('.title').empty();
$('.container').empty();

let id = $('.id').val();

$.ajax({
	url: "Review",
	method: "post",
	data: { id: id }
}).done(function(response) {
	const list = JSON.parse(response);
	if (list != null) {
		$('.title').append(
			`<tr>
				<th>가게명</th>
				<th>코멘트</th>
				<th>등록일</th>
				<th>평점</th>
		</tr>`
		)

		list.forEach(e => {
			const no = e.no;
			const code = e.resto_code;
			const res_name = e.user_id;		
			const comment = e.coment;
			const reg_date = e.reg_date.split(" ")[0];
			const give_grade = e.give_grade;

			$('.container').append(
				`<tr>
                <td><a href="restaurantViewForm?code=${code}">${res_name}</a></td>
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