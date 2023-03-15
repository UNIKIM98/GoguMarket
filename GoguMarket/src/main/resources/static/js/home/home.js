/**
 * 
 */

console.log("home오ㅑㅏㅆ니..........?")
//===========================================================
// ❤️ 전역변수 선언부
var auctList;
var dealList;
var snsList;
var bizList;

//===========================================================
// ❤️ DOM 이전 실행부
$(function() {
	selectHomeListAjax("한식");

})

//===========================================================
// ❤️ 전체 정보 가져오는 Ajax
function selectHomeListAjax(ctgry) {
	$.ajax({
		url: "/goguma/selectHomeListAjax/" + ctgry,
		type: 'get',
		dataType: "json",
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			dealList = result.deal;
			makeDealList(dealList);

			auctList = result.auct;
			makeAuctList(auctList);


			snsList = result.sns;
			makeSnsList(snsList);

			bizList = result.biz;
			makeBizList(bizList);

		},
		error: function(err) {
			console.log(err);
		}
	})
}

//===========================================================
// ❤️ 중고거래 부분 그리기
function makeDealList(dealList) {
	console.log(dealList);
	let dealT = $("#dealTrgt");
	dealList.forEach((deal)=>{
		dealT.append(
			`
			<div class="blog-item">
				<img src="${deal.ATCH_PATH}" alt="">
					<div class="blog-desc">
						<h5 class="blog-title">
							<a href="#">${deal.DL_TTL}</a>
						</h5>
						<p>${deal.DL_CN}</p>
					<div class="read-more">
						<a href="/goguma/dealdetail/${deal.DL_NO}">Read more</a>
					</div>
					<ul class="blog-meta">
						<li><a href="#"><i class="zmdi zmdi-favorite"></i>단골수</a></li>
						<li><a href="#"><i class="zmdi zmdi-comments"></i>예약수</a></li>
					</ul>
				</div>
			</div>
			`
		)
	})
}

//===========================================================
// ❤️ 경매 부분 그리기
function makeAuctList(auctList) {
	console.log(auctList);

}
//===========================================================
// ❤️ 동네생활 부분 그리기
function makeSnsList(snsList) {
	console.log(snsList);

}

//===========================================================
// ❤️ 동네가게 부분 그리기
function makeBizList(bizList) {
	console.log(bizList);

}