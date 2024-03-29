

var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면

var btn = document.getElementById("myBtn"); // 글쓰기 활성화 버튼

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns"); //개인 sns 창 화면
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");

btn.onclick = function() { //글쓰기 창 활성화
	modal.style.display = "block";

};

function insertSns() {
	modal.style.display = "block"; //글쓰기 창 활성화

}




//------------------ 여기까지 모달-------------------------


//------------------ 개인 게시글 시작-------------------------

function snsModal(id) {
	//활상화
	Sns.style.display = "block"; //개인 게시글 창 활성화


	$("#replyUserId").val("[[${session.userId}]]")
	//전 이벤트 자식중 아이디값과 이미지 아이디 값을 가져옴
	//단건을 조회하는 ajax를 실행
	console.log(id);
	Number(id);

	//단건조회
	$.ajax({
		url: "/goguma/selectSns",
		type: "GET",
		data: { id },
		dataType: "json",
	}).then((obj) => { // 해당 게시글 정보를 modal 창에 추가

		$("#mySns #snsNo").val(obj.sns.snsNo);
		$("#CN").text(obj.sns.snsCn);
		$("#snsYmd").text(obj.sns.snsYmd);
		$("#atchPath").attr("src", obj.atch[0].atchPath);
		$("#miniId").text(obj.sns.userId + "@gogu.ma");
		$("#replySnsNo").val(obj.sns.snsNo);

	});
	SelectCmntlist(id); //댓글 조회
	//리턴된 값을 태그를 찾아서 넣음
}

/*function checkParReply(item, groupNo) {
	// 아이템 오브젝트 포리치 돌려서 같은 item.cmntNo가 groupNo인 사람이 있는지 찾아야함
	// 아니면 댓글 삭제할때 db에서 같은 groupNo인거 그냥 다 날리자!
}
*/

function getUser() {// 세션에 담긴 회원 정보를 가져옴

	$.ajax({
		url: "/goguma/getUser",
		type: "get",
		dataType: "json",
		success: function(data) {
			console.log(data)
			$(data).each(function(i, item) {

				user[i] = item.mem;
			})
		}, error: function(error) {
			console.log(error)
		}
	})

}


function SelectCmntlist(snsNo) { //해당 게시글 번호를 가지고 댓글 답글 조회


	var forCnt = 0;
	$.ajax({
		url: "/goguma/SelectCmntlist",
		type: "GET",
		data: { snsNo },
		dataType: "json",
		success: function(data) {


			console.log(data);
			$("#Sns-reply").empty();
			$(data).each(function(index, item) {
				if (item.groupNo > 0) { // 답글
					$("#loadDiv").load("/sns/rrpView.html", function(result) { // 답글 뷰 페이지를 로드해옴
						var addedDiv = $("#rrplyGroup_" + item.groupNo).append(result); //답글 정보를 append
						$(addedDiv).find(".rrpBox").each(function(index, obj) {
							if ($(obj).hasClass("set") == false) { //set 이라는 class 이름을 가지고 있으면 false
								$(obj).addClass("set"); 		   //단건을 추가하고 추가완료된 단건에는 class="set"
								$(obj).find("#rrpView").attr('action', 'rreplyEdit(' + item.cmntNo + ',' + item.snsNo + ',' + item.cmntMem);
								$(obj).find("#rrpContent").attr("id", "rrpContent" + item.cmntNo);
								$(obj).find("#rrpAtchPath").attr("src", item.atchPath);
								$(obj).find("#rrpGroupNo").val(item.groupNo);
								$(obj).find("#rrpCmntNo").val(item.cmntNo);
								$(obj).find("#rrpSnsNo").val(item.snsNo);
								$(obj).find("#rrpCmntMem").text(item.cmntMem);
								$(obj).find("#rrpCmntYmd").text(item.cmntYmd);
								$(obj).find("#cmntCn").text(item.cmntCn);
								
								if ((user[0].userId == item.cmntMem) == true) { //세션 아이디와 답글 작성자가 같으면 수정 삭제 버튼 활성화
									$(obj).find("#delbutton").attr("onclick",)
									$(obj).find("#editbutton").attr("onclick", "rreplyEditForm(" + item.cmntNo + "," + item.snsNo + ")")
								} else {										//세션 아이디와 답글 작성자가 다르면 제거
									$(obj).find("#delbutton").remove();
									$(obj).find("#editbutton").remove();
								}
								return true;
							}
						});
					});
				} else {
					console.log("cmntCn : " + item.cmntCn);
					forCnt++;

					$("#loadDiv").load("/sns/replyView.html", function(result) { // 답글과 같음
						var addedDiv = $("#Sns-reply").append(result);
						$(addedDiv).find(".replyGroup").each(function(index, obj) {
							if ($(obj).hasClass("set") == false) {
								$(obj).addClass("set");
								$(obj).addClass("numbering" + forCnt);
								$(obj).find("#rpAtchPath").attr("src", item.atchPath);
								$(obj).find("#rpGroupNo").val(item.groupNo);
								$(obj).find("#rpCmntNo").val(item.cmntNo);
								$(obj).find("#rpSnsNo").val(item.snsNo);
								$(obj).find("#cmntMem").text(item.cmntMem);
								$(obj).find("#cmntYmd").text(item.cmntYmd);
								$(obj).find("#cmntCn").text(item.cmntCn);
								$(obj).find("#cmntCn").attr('id', 'cmntCn' + item.cmntNo);
								$(obj).find(".name-commenter").attr('id', 'replyGroup' + item.cmntNo);
								$(obj).find("#reInput").attr('onclick', 'ShowRrpInput(' + item.cmntNo + ',' + item.snsNo + ')');
								$(obj).find(".replyGroup").attr('id', 'replyGroup' + item.cmntNo);
								$(obj).find(".rrplyGroup").attr('id', 'rrplyGroup_' + item.cmntNo);

								if ((user[0].userId == item.cmntMem) == true) {
									$(obj).find("#editbutton").attr("onclick", "replyEditForm(" + item.cmntNo + "," + item.snsNo + "," + item.groupNo + "," + "'" + item.cmntCn + "'" + ")")
									$(obj).find("#replydel").attr('onclick', "rreplyDel(" + item.cmntNo + ", " + item.groupNo + "," + item.snsNo + ")");
								} else {

									$(obj).find("#editbutton").remove();
									$(obj).find("#replydel").remove();
								}
								return true;
							}

						})

					});
				}
				return true;
			});
		},
		error: function() {
			console.log(error)
		},
		async: false
	});
}



function ShowRrpInput(cmntNo, replySnsNo) {		
	console.log("내가 띄운 답답답글의 번호 : " + cmntNo);

	$("#loadDiv").load("/sns/rrpWrite.html", function(result) {
		var addedDiv = $("#rrplyGroup_" + cmntNo).append(result);
		$(addedDiv).find("#groupNo").val(cmntNo);
		$(addedDiv).find("#replySnsNo").val(replySnsNo);
		console.log("생성된 div ID : " + sessionID);
	});

}




//---------------------------------reply(insert)-----------------------------------
function insertReply() {
	console.log("댓긋 입")
	let snsNo = $("#replySnsNo").val();
	let cmntMem = $("#replyUserId").val();
	let cmntCn = $("#replyCmntCn").val();

	console.log(event.currentTarget.parentNode.parentNode.parentNode.sibling)


	let recmntOrder = $("#recmntOrder").val();
	let groupNo = $("#groupNo").val();
	let step = $("#step").val();

	console.log(recmntOrder)
	console.log(groupNo)
	console.log(step)



	if (cmntCn == " ") {
		alert("댓글을 입력하세요");
		return;
	}

	$.ajax({
		url: "/goguma/insertReply",
		type: "POST",
		data: JSON.stringify({
			snsNo: snsNo,
			cmntMem: cmntMem,
			cmntCn: cmntCn,
		}),
		contentType: "application/json",

		success: function(data) {
			if (data == "") {
				alert("댓글 등록에 실패했습니다.");
			} else {
				alert("댓글 등록에 성공했습니다.");
				$("#Sns-reply").empty();
				SelectCmntlist(snsNo);
			}
		},
		error: function() {
			console.log(error)
		}
	});


}



function insertRrp() {
	let snsNo = $(".rrpBox #replySnsNo").val();
	let cmntCn = $(".rrpBox #replyCmntCn").val();
	let groupNo = $(".rrpBox #groupNo").val();

	console.log("groupNo : " + groupNo);
	console.log("snsNo : " + snsNo);
	console.log("cmntCn : " + cmntCn);


	if (cmntCn == " ") {
		alert("답글을 입력하세요");
		return;
	}


	$.ajax({
		url: "/goguma/insertReply",
		type: "POST",
		data: JSON.stringify({
			snsNo: snsNo,
			cmntCn: cmntCn,
			groupNo: groupNo,
		}),
		contentType: "application/json",

		success: function(data) {
			if (data == "") {
				alert("답글 등록에 실패했습니다.");
			} else {
				alert("답글 등록에 성공했습니다.");
				$("#Sns-reply").empty();
				SelectCmntlist(snsNo);
			}
		},
		error: function() {
			cosole.log(error)
		}
	});


}

function updateSns() {

}

function deleteSns() {

}


function replyEditForm(rpCmntNo, rpSnsNo, rpGroupNo, rpCmntCn) {

	let button = event.currentTarget.parentNode;


	$('#cmntCn' + rpCmntNo).remove();
	$("#replyGroup" + rpCmntNo).append(`<textarea class="custom-textarea" id="cmntCn" name="cmntCn">${rpCmntCn}</textarea>`);

	event.currentTarget.remove();
	$(button).append(`<input type="submit" class="btn btn-dark mt-3 f-left"
								 style="margin: 10px" id="editButton" onclick="rreplyEdit(${rpSnsNo},${rpGroupNo})" value="수정" rows="1000" cols="8">`)
}


//=========================답글 수정============================

function rreplyEditForm(rrpCmntNo, rrpSnsNo) { //답글 수정폼 생성

	/*console.log('넘어옴');
	$("#rrpContent #rrpCmntCn").remove();
	$("#editForm").remove();
	
	$("#rrpContent #rrpCmntCn").remove();
	
	$("#rrpContent").append(
		'<textarea id="innerdiv" class="form-inline" id="rrpCmntCn" style="word-break: break-all; ""width:200px">Hi there!</textarea>'	
	)*/

	let cn = $('#rrpContent' + rrpCmntNo + ' #cmntCn').text()
	$('#rrpContent' + rrpCmntNo + ' #cmntCn').remove();

	$("#rrpContent" + rrpCmntNo).append(`<textarea class="custom-textarea" id="cmntCn1" name="cmntCn">${cn}</textarea>`);

	let button = event.currentTarget.parentNode;

	event.currentTarget.remove();

	$(button).append(`<input type="submit" class="btn btn-dark mt-3 f-right"
								 style="margin: 10px" id="editButton" onclick="rreplyEdit(${rrpSnsNo})" value="수정" rows="300" cols="8">`)


}

function rreplyEdit(rrpSnsNo, rpGroupNo) { //답글 수정



	console.log(rpGroupNo)


	var formData = {};

	if (rpGroupNo != 0) {
		console.log($(rrpCmntNo).val())

		formData = new FormData($("#rrpView")[0]);
		console.log(formData)

	} else {
		console.log($(rpCmntNo).val())
		formData = new FormData($("#editForm")[0]);
		console.log(formData)
	}


	$.ajax({
		url: "/goguma/rreplyEdit",
		type: "POST",
		data: formData,
		processData: false,
		contentType: false,

		success: function(data) {
			if (data == null) {
				console.log('수정에 실패했습니다.')
				$("#Sns-reply").empty();
				SelectCmntlist(rrpSnsNo);
			} else {
				console.log('수정성공했습니다.')
				$("#Sns-reply").empty();
				SelectCmntlist(rrpSnsNo);
			}
		},
		error: function() {
			console.log(error)
		}
	});


}

function rreplyDel(rrpCmntNo, rrpGroupNo, rrpSnsNo) { //댓글 답글 삭제


	let confirmMessage = confirm('정말 삭제 하시겠습니까?')

	if (confirmMessage)


		$.ajax({
			url: "/goguma/deleteRreply",
			type: "POST",
			data: JSON.stringify({
				cmntNo: rrpCmntNo,
				groupNo: rrpGroupNo,
			}),
			contentType: "application/json",

			success: function(data) {
				if (data != null) {
					$("#Sns-reply").empty();
					alert('삭제가 완료 되었습니다.')
				}
				SelectCmntlist(rrpSnsNo);

			},
			error: function() {
				console.log(error)
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





