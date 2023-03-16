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
var today = new Date();

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
	let dealT = $(".dealTrgt");
	dealList.forEach((deal) => {
		dealT.append(
			`
			<div class="blog-item" style="width:380px ; height:300px">
				<img src="${deal.ATCH_PATH}" alt="" style="width:380px ; height:300px">
					<div class="blog-desc" style="width:380px ; height:300px">
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
	auctList.forEach((auct) => {
		$(".auctTrgt").append(`

                        
                        <div class="col-lg-4 d-block d-md-none d-lg-block">
                            <div class="banner-item banner-1">
                                <div class="ribbon-price">
                                    <span>마감임박</span>
                                </div>
                                <div class="product-item"  style="width:380px ; height:300px">
                                    <div class="banner-img"  style="width:380px ; height:300px">
                                        <a href="/goguma/auctSelect/${auct.AUCT_NO}"><img src="${auct.ATCH_PATH}" alt="경매상품이미지" style="width:380px ; height:300px"></a>
                                    </div>
                                    <div class="product-info">
                                        <h6 class="product-title">
                                            <a href="/goguma/auctSelect/${auct.AUCT_NO}">${auct.AUCT_TTL}</a>
                                        </h6>
                                        <div class="pro-rating">
                                        </div>
                                        <h3 class="pro-price">현재 가격 자리</h3>
                                        <h4 class="pro-price">경매 마감까지 ${auct.DDLN_YMD}일 남았습니다.</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
	`)
	// 데드라인 설정하기(집에서 고)
//		let dDay = new Date(auct.DDLN_YMD).format("YYYY-MM-DD");
//		console.log(dDay + "데드라인와엠디");
//		let timeDiff = dDay.getTime() - today.getTime();
//		let daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
//		console.log(daysDiff+'일 남았습니다!')
	})

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