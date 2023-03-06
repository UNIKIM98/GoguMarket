/**
 * 경매 메인페이지
 * 
 * 
 * 
 * 
 * 
 * var span = document.getElementsByClassName("close1")[0];
 */
//📌	 공통변수 선언부
var fileCount = 0; //현재 첨부파일 개수
var totalCount = 10; // 첨부 가능한 파일 개수
var fileNum = 0; //첨부파일 인덱스
var inputFileList = new Array(); // insert 파일 배열
var deleteList = new Array(); // delete 파일 배열
var isSubmitOk = true; //submit 유효성 체크 boolean

var img_style = 'width:100px;height:100px;z-index:none'; // 미리보기 이미지 속성
var exCnt = 0;
        






const remainTime = document.querySelector("#remain-time");
function diffDay(){
    const ddln = new Date("2023-12-25");
    const todayTime = new Date();

    const diff = ddln - todayTime;

    const diffDay = Math.floor(diff / (1000*60*60*24));
    const diffHour = Math.floor(diff / ((1000*60*60)) % 24);
    const diffMin = Math.floor(diff / ((1000*60)) % 60);
    const diffSec = Math.floor(diff / 1000 % 60);

    remainTime.innerText = `${diffDay}일 ${diffHour}시간 ${diffMin}분 ${diffSec}초`;
}

diffDay();
setInterval(diffDay, 1000);


/**/
            $.ajax({
                url : "/auctSelect/{auctNo}",
                type : "Post",
                dataType : 'json',
                contentType : 'application/json; charset = utf-8',
                success : function(result) {
                    console.log(result)
                    



                },
                error : function(err) {
                    console.log(err);
                }
            })




//✅ exFile 중에서 삭제할 파일 정보 
function deleteFile(id, atchId, atchNo) {
    $("#"+id).remove();
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
    if(fileCount==0){
        $("#fileErrMsg").text("✅ 상품 사진을 등록해주세요.")
        isSubmitOk = false;
    }
}
//✅ 파일 삭제 ajax
function exFileDeleteAjax(){
    $.ajax({	
        url : "/deleteTest",
        type : "post",
        data :  JSON.stringify(deleteList),
        contentType: "application/json; charset=utf-8",
        success : function(delCnt) {
                console.log(delCnt)
        },
        error : function(err){
            alert("[삭제실패] 첨부파일 삭제 중 오류가 발생하였습니다. :(");
        }
    });	
}












// 모달 나가기 - 모달 외에 화면을 누르면 모달창 상태를 none 으로 바꿈
window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}

	if (event.target == Sns) {
		Sns.style.display = "none";
	}
};

// 글쓰기 창 나가기
span.onclick = function() {
	modal.style.display = "none";
};


