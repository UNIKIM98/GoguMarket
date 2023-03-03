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

		SelectCmntlist(obj.sns.snsNo);
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
			$(data).each(function(index, item) {
				console.log(item.cmntCn);

				$("#Sns-reply").append(`
						<form id="reply" action="#">
						<input type="hidden" name="groupNo" id="groupNo" th:value="${item.groupNo}">
						<input type="hidden" name="step" id="step" th:value="${item.step}">
						<input type="hidden" name="recomntOrder" id="recomntOrder" th:value="${item.recmntOrder}">
				<div class="card mb-1">
					<div class="card-body bg-light">

						<div class="media" style="margin: 10px">
							<div class="media-left">
								<a href="#"><img class="media-object" src="img/author/1.jpg"
									alt="#"></a>
							</div>
							<div class="media-body ml-10">
								<div class="clearfix">
									<div class="name-commenter pull-left">

										<h6 class="media-heading">
											<input type="hidden" name="snsNo1" id="snsNo1"> <a
												href="#" id="cmntMem">${item.cmntMem}</a> <br> <a href="#"
												style="opacity: 50%" id="cmntYmd">${item.cmntYmd}</a>
										</h6>
										<div class="form-inline" id="cmntCn" style="word-break:break-all;">${item.cmntCn}</div>
									</div>
								</div>

							</div>
						</div>
							<button type="button" class="btn btn-dark f-right"
											onClick="rereplyInput()">reply</button>
					</div>

				</div>
			</form>
				`);
			});

		},
	});
}

//---------------------------------reply(insert)-----------------------------------
function insertReply() {
	let snsNo = $("#replySnsNo").val();
	let cmntMem = $("#replyUserId").val();
	let cmntCn = $("#replyCmntCn").val();

	console.log(snsNo);
	console.log(cmntMem);
	console.log(cmntCn);

	if (cmntCn == "") {
		console.log("댓글을 입력하세요");
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
			SelectCmntlist(snsNo);
		},
	});
	console.log("test");
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
