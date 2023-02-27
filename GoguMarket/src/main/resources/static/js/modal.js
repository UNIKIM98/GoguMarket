
var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면 

var btn = document.getElementById("myBtn"); // 글쓰기 활성화

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns");
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");



btn.onclick = function() {
	console.log('gd')
	modal.style.display = "block";
}


function snsModal(id) {

	//When the user clicks the button, open the modal
	Sns.style.display = "block";

	//전 이벤트 자식중 아이디값과 이미지 아이디 값을 가져옴
	let sns = document.getElementById("clickSns")


	//단건을 조회하는 ajax를 실행
	console.log(id)

	//단건조회
	$.ajax({
		url:"/selectSns",
		type: "GET",
		data: {id},
		dataType: 'json',
      })
		.then(obj => {
			console.log(obj.sns.snsCn)
		$("#CN").text(obj.sns.snsCn)
			console.log('back')
		
		});

	//리턴된 값을 태그를 찾아서 넣음


}

// 모달 나가기 - 모달 외에 화면을 누르면 모달창 상태를 none 으로 바꿈

window.onclick = function(event) {
	if (event.target == modal) {
		modal.style.display = "none";
	}

	if (event.target == Sns) {
		Sns.style.display = "none";
	}
}

// 글쓰기 창 나가기 함수
span.onclick = function() {
	modal.style.display = "none";
}
