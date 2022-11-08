/**
 * 
 */
 $('.title').empty();
 $('.container').empty();

 console.log("이제 곧 게시글 목록 찍을거야");

 let id = $('.id').val();
console.log("id : " + id);

 $.ajax({
    url: "Board",
    method: "post",
    data: { id : id}
 }).done(function (response){
    const list = JSON.parse(response);
    
    console.log("list : " + list);

	if(list != null){
	    $('.title').append(
	        `<tr>
					<th>번호</th>
					<th>제목</th>
					<th>등록일</th>
					<th>수정일</th>
					<th>조회수</th>
			</tr>`
	    )
	
	    list.forEach(e => {
	        const no = e.no;
	        const title = e.title;
			console.log("title : " + title);
	        const content = e.content;
	        const reg_date = e.reg_date.split(" ")[0];
			let mod_date = "-";
			if(e.mod_date != null){
	        	mod_date = e.mod_date.split(" ")[0];				
			}
	        const view_cnt = e.view_cnt;
	
	        $('.container').append(
	            `<tr>
	                <td>${no}</td>
	                <td><a href="boardViewForm?no=${no}">${title}</a></td>
	                <td>${reg_date}</td>
	                <td>${mod_date}</td>
	                <td>${view_cnt}</td>
	            </tr>`
	        );
	    });		
	}else{
		$('.title').html("작성한 게시글이 없습니다");
	}


 })