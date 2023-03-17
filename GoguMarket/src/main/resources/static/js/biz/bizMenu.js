/**
 * 
 */


console.log("왔님.ㅣ.......")
$(function() {
	selectBizMenuListAjax();
})

function selectBizMenuListAjax() {
	/*<![CDATA[*/
	//1. 카테고리
	var bizNo = /*[[ ${session} ]]*/
	console.log(/*[[ ${session} ]]*/)

		/*]]*/
		$.ajax({
			url: '/biz/selectBizMenuListAjax/' + bizNo
		})
}