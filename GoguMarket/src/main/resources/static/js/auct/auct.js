/**
 * 경매 메인페이지
 */

var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면

var btn = document.getElementById("myBtn"); // 글쓰기 활성화

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns");
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");

var replyInput = document.getElementById("replyInput"); //인ㅍㅅ


var reInput = document.getElementById("reInput");
//📌	 공통변수 선언부
var fileCount = 0; //현재 첨부파일 개수
var totalCount = 10; // 첨부 가능한 파일 개수
var fileNum = 0; //첨부파일 인덱스
var inputFileList = new Array(); // insert 파일 배열
var deleteList = new Array(); // delete 파일 배열
var isSubmitOk = true; //submit 유효성 체크 boolean


btn.onclick = function() {
	modal.style.display = "block";
    disappear.style.display = "none";

};
//------------------ 개인 게시글-------------------------
function insertSns(){
	modal.style.display = "block"; //개인 게시글 창 활성화

}
$.ajax({
    url: "/auctSelect/{auctNo}",
    type: "Post",
    dataType: 'json',
    contentType: 'application/json; charset = utf-8',
    success: function (result) {
        console.log(result)




    },
    error: function (err) {
        console.log(err);
    }
})




//✅ exFile 중에서 삭제할 파일 정보 
function deleteFile(id, atchId, atchNo) {
    $("#" + id).remove();
    deleteList.push({
        "atchId": Number(atchId),
        "atchNo": Number(atchNo)
    })
}
//✅ 화면/배열에서 delete 실행하는 함수			
function fileDelete(fileNum) {
    var no = fileNum.replace(/[^0-9]/g, "");
    inputFileList[no].is_delete = true;
    $('#' + fileNum).remove();
    fileCount--;
    if (fileCount == 0) {
        $("#fileErrMsg").text("✅ 상품 사진을 등록해주세요.")
        isSubmitOk = false;
    }
}
//✅ 파일 삭제 ajax
function exFileDeleteAjax() {
    $.ajax({
        url: "/deleteTest",
        type: "post",
        data: JSON.stringify(deleteList),
        contentType: "application/json; charset=utf-8",
        success: function (delCnt) {
            console.log(delCnt)
        },
        error: function (err) {
            alert("[삭제실패] 첨부파일 삭제 중 오류가 발생하였습니다. :(");
        }
    });
}

// 모달 나가기 - 모달 외에 화면을 누르면 모달창 상태를 none 으로 바꿈
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }

    if (event.target == Sns) {
        Sns.style.display = "none";
    }
};

// 글쓰기 창 나가기
span.onclick = function () {
    modal.style.display = "none";
};