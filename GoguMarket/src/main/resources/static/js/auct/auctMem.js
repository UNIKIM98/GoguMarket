/**
 * 입찰 등록
 */

var btn = document.getElementById("myBtn"); // 글쓰기 활성화

var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns");
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");


btn.onclick = function() {
	console.log("gd");
	modal.style.display = "block";
};

//----------------개인 게시글-------------------------



