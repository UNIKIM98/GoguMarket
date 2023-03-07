var asdsad =[];

$(document).ready(function() {
	alert("실행")
	let key = $("#search").val();
	let sort = $("#sort").val();

	console.log(key);
	console.log(sort);
	
	/*$.ajax({
		url:"/selectMemberList",
		type:"GET",
		data: {key,sort},
		dataType:'JSON',
		contentType : 'application/json; charset = utf-8',
		
		success:function(){
			
		},
		error: fuction(error){
			console.log(error);
		}
	});
	
});*/

	$.ajax({
			url : "/selectSnsList", // 데이터를 가져올 경로 설정
			type : "GET", // 데이터를 가져오는 방식
			dataType : 'json',
			contentType : 'application/json; charset = utf-8',
			success : function(result) {
					  console.log(result)
					  for(sns of result){
						$("#list").append(`<div class="brand-item">
	                            				<div class="single-brand-product"
													id="clickSns"
													onclick="snsModal(${sns.snsNo})">
	                            						${sns.userId} / ${sns.snsYmd}
	                            						<input type="hidden" id="snsNo" name="snsNo" value="${sns.snsNo}">
	                            						<input type="hidden" id="userId" name="userId" value="${sns.userId}">
	                         							<img src="${sns.atchPath}" alt=""></a>
	                         							조회수&nbsp👀:${sns.inqCnt}&nbsp;&nbsp; 좋아요&nbsp;👍🏻: ${sns.likeNocs}
												</div>
											</div>`)
	           							 }
										},
					error : function(err) {
						console.log(err);
					}
				})