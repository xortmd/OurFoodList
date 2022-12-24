/**
 * 
 */
$('.title').empty();
$('.container').empty();
let id = $('.id').val();

$.ajax({
	url: "Like",
	method: "post",
	data: { id: id }
}).done(function(response) {
	const list = JSON.parse(response);
	if (list != null) {
		$('.title').append(
			`<tr>
				<th>가게명</th>
				<th>가게주소</th>
				<th>전화번호</th>
				<th>음식종류</th>
				<th>평점</th>
			</tr>`
		)

		list.forEach(e => {
			const code = e.code;
			const res_name = e.res_name;
			const address = e.address;
			const res_phone = e.res_phone;
			const kind = e.kind;
			const ave_grade = e.ave_grade;

			$('.container').append(
				`<tr>
                <td><a href="restaurantViewForm?code=${code}">${res_name}</a></td>
                <td>${address}</td>
                <td>${res_phone}</td>
                <td>${kind}</td>
                <td>${ave_grade}</td>
            </tr>`
			);
		});
	} else {
		$('.title').html("아직 좋아요한 맛집이 없습니다:(");
	}
})