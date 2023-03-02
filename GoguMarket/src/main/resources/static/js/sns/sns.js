var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면

var btn = document.getElementById("myBtn"); // 글쓰기 활성화

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns");
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");

btn.onclick = function() {
	console.log("gd");
	modal.style.display = "block";
};

//----------------개인 게시글-------------------------

function snsModal(id) {
	//활상화
	Sns.style.display = "block"; //개인 게시글 창 활성화

	//전 이벤트 자식중 아이디값과 이미지 아이디 값을 가져옴
	$("#snsNo1").val(id);
	$("#replySnsNo");
	//단건을 조회하는 ajax를 실행
	console.log(id);
	Number(id);

	//단건조회
	$.ajax({
		url: "/selectSns",
		type: "GET",
		data: { id },
		dataType: "json",
	}).then((obj) => {
		$("#CN").text(obj.sns.snsCn);
		$("#snsYmd").text(obj.sns.snsYmd);
		$("#atchPath").attr("src", obj.atch[0].atchPath);
		$("#miniId").text(obj.sns.userId + "@gogu.ma");
		$("#replySnsNo").val(obj.sns.snsNo);
		console.log(obj.sns.snsNo);

		SelectCmntlist(obj.sns.snsNo); //댓글 전체 조회
	});
	//리턴된 값을 태그를 찾아서 넣음
}

function SelectCmntlist(snsNo) {
	console.log(snsNo);

	$.ajax({
		url: "/SelectCmntlist",
		type: "GET",
		data: { snsNo },
		dataType: "json",
		success: function(data) {
			console.log(data);
			$("#Sns-reply").replaceWith(data)
			},
	});
}

//---------------------------------reply(insert)-----------------------------------
function insertReply() {
	let snsNo = $("#replySnsNo").val();
	let cmntMem = $("#replyUserId").val();
	let cmntCn = $("#replyCmntCn").val();

	console.log(typeof Number(snsNo)); //인트형으로 타입변환
	snsNo = Number(snsNo);
	console.log(snsNo);
	console.log(cmntMem);
	console.log(cmntCn);

	if (cmntCn == "") {
		alert("답글을 입력해주세요");
		return;
	}

	$.ajax({
		url: "/insertReply",
		type: "POST",
		data: JSON.stringify({
			snsNo: snsNo,
			cmntMem: cmntMem,
			cmntCn: cmntCn,
		}),
		contentType: "application/json",

		success: function(data) {
			if (data == "") {
				alert("게시물 등록에 실패했습니다.");
			} else {
				alert("게시물 등록에 성공했습니다.");
			}
		},
	});
	SelectCmntlist();
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
