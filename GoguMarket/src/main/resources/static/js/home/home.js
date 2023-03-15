/**
 * 
 */

console.log("home오ㅑㅏㅆ니..........?")
// 전역변수 선언부
var auctList;
var dealList;
var snsList;
var bizList;

// DOM 이전 실행부
$(function() {
	selectHomeListAjax("한식");

})

//전체 list Ajax
function selectHomeListAjax(ctgry) {
	$.ajax({
		url: "/goguma/selectHomeListAjax/"+ctgry,
		type: 'get',
		dataType: "json",
		contentType: "application/json",
		success: function(result) {
			console.log(result);
			
			auctList = result.auct;
			console.log(auctList);
			
			dealList = result.deal;
			console.log(dealList);
			
			snsList = result.sns;
			console.log(snsList);
			
			bizList = result.biz;
			console.log(bizList);
			
		},
		error: function(err) {
			console.log(err);
		}
	})
}