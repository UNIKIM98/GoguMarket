//===mybook01에서 사용

//모달창 선택
var bookmodal = document.getElementById("myBookModal")

//버튼 선택
var modalbtn = document.getElementById("modalBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

//버튼클릭시 모달창 활성화
modalbtn.onclick = function(){
    bookmodal.style.display = "block";
}

//x클릭시 모달창 닫힘
span.onclick = function() {
    bookmodal.style.display = "none";
}

//모달창 이외 클릭시 모달창 닫힘
window.onclick = function(event) {
  if (event.target == bookmodal) {
    bookmodal.style.display = "none";
  }
}