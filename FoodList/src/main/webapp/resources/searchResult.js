
function printResult() {
	$('.title').empty();
	$('.container').empty();	
	
	let kind = $('#kind').val();
	let area = $('#area').val();
	let sort = $('#sort').val();
	
	$.ajax({
		medtod: "POST",
		url: "RestaurantSearch",
		data: {
			kind: kind,
			area: area,
			sort: sort
		}
	}).done(function (response) {
		
		const list = JSON.parse(response);
		
		$('.title').append(
			`<tr>
				<th>가게명</th>
				<th>전화번호</th>
				<th>음식종류</th>
				<th>작성일자</th>
				<th>평점</th>
				<th>좋아요</th>
				<th>리뷰</th>
			</tr>`
		);
		
		list.forEach(e => {
            const code = e.code;
            const res_name = e.res_name;
			const res_phone = e.res_phone;
            const kind = e.kind;
            const str = e.reg_date;
            const reg_date = str.substring(0, 10);
            const ave_grade = e.ave_grade;
            const like_cnt = e.like_cnt;
            const review_cnt = e.review_cnt;

            $('.container').append(
                `<tr>
					<td><a href="restaurantViewForm?code=${code}">${res_name}</a></td>
					<td>${res_phone}</td>
					<td>${kind}</td>
					<td>${reg_date}</td>
					<td>${ave_grade}</td>
					<td>${like_cnt}</td>
					<td>${review_cnt}</td>
				</tr>`
            );
        });
    });
}