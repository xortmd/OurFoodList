document.getElementById(a).style.color= "blue";
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
			`<dl>
				<dt>가게명</dt>
				<dt>가게주소</dt>
				<dt>전화번호</dt>
				<dt>음식종류</dt>
				<dt>작성일자</dt>
				<dt>평점</dt>
				<dt>좋아요 개수</dt>
				<dt>리뷰 개수</dt>
			</dl>`
		);
		
		list.forEach(e => {
            const code = e.code;
            const res_name = e.res_name;
            const address = e.address;
            const res_phone = e.res_phone;
            const kind = e.kind;
            const reg_date = e.reg_date;
            const ave_grade = e.ave_grade;
            const like_cnt = e.like_cnt;
            const review_cnt = e.review_cnt;

            $('.container').append(
                `<dl>
					<dt><a href="restaurantViewForm?code=${code}">${res_name}</a></dt>
					<dt>${address}</dt>
					<dt>${res_phone}</dt>
					<dt>${kind}</dt>
					<dt>${reg_date}</dt>
					<dt>${ave_grade}</dt>
					<dt>${like_cnt}</dt>
					<dt>${review_cnt}</dt>
				</dl>`
            );
        });
    });
}