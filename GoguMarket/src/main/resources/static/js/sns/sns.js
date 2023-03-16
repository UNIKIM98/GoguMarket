$(document).ready(function(){

	 keyValue();
})

var modal = document.getElementById("myModal"); // 글쓰기창 전체 화면

var btn = document.getElementById("myBtn"); // 글쓰기 활성화

var span = document.getElementsByClassName("close1")[0]; //글쓰기 비활성화 위한 x버튼

var Sns = document.getElementById("mySns");
// Get the button that opens the modal
var Snsbtn = document.getElementById("clickSns");

var replyInput = document.getElementById("replyInput"); //인ㅍㅅ


var reInput = document.getElementById("reInput");
/*const rrpWriteHtml;
*/

//외부 파일 로드
/*$(document).ready(function()
{
  $.get("../templates/sns/rrpWrite.html", function(html_string)
   {
		console.log("테스트 입니다 html 파일 : " + html_string);
	  alert(html_string); 
	  rrpWriteHtml = html_string;
   },'html');
});
*/


reInput.onclick = function() {
	reInput.style.display = "block";

};

//----------------공통코드 출력-------------------------

function keyValue() {

	$.ajax({
		url:"/keyValue",
		type: "GET",
		async: false,
		dataType: "JSON",
		contentType: "application/json; charset = utf-8",

		success: function(data) {
			console.log(data);
			$(".pstSe").find("#pstSe").append(
				`<option value="">전체</option>`);
			$(data["searchlist"])
				.each(
					function(index, obj) {
						$(".pstSe")
							.find("#pstSe")
							.append(
								`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`);
					});

		},
		error: function(data) {
			console.log(error);
		},
	});
}



//------------------ 개인 게시글-------------------------

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

	});
	SelectCmntlist(id);
	//리턴된 값을 태그를 찾아서 넣음
}


function checkParReply(item, groupNo) {
	// 아이템 오브젝트 포리치 돌려서 같은 item.cmntNo가 groupNo인 사람이 있는지 찾아야함
	//아니면 댓글 삭제할때 db에서 같은 groupNo인거 그냥 다 날리자!

}


function SelectCmntlist(snsNo) {
	var forCnt = 0;
	$.ajax({
		url: "/SelectCmntlist",
		type: "GET",
		data: { snsNo },
		dataType: "json",
		success: function(data) {
			console.log(data);
			$(data).each(function(index, item) {
				if (item.groupNo > 0) {
					$("#loadDiv").load("sns/rrpView.html", function(result) {
						var addedDiv = $("#rrplyGroup_" + item.groupNo).append(result);
						$(addedDiv).find(".rrpBox").each(function(index, obj) {
							console.log("넘어옴 : " + index);
							if ($(obj).hasClass("set") == false) {
								$(obj).addClass("set");
								$(obj).find("#rrpView").attr('action', 'rreplyEdit(' + item.cmntNo + ',' + item.snsNo + ',' + item.cmntMem + ')');
								$(obj).find("#rrpContent").attr("id", "rrpContent" + item.cmntNo);
								$(obj).find("#rrpGroupNo").val(item.groupNo);
								$(obj).find("#rrpCmntNo").val(item.cmntNo);
								$(obj).find("#rrpSnsNo").val(item.snsNo);
								$(obj).find("#rrpCmntMem").text(item.cmntMem);
								$(obj).find("#rrpCmntYmd").text(item.cmntYmd);
								$(obj).find("#cmntCn").text(item.cmntCn);
								$(obj).find("#delbutton").attr("onclick", "rreplyDel(" + item.cmntNo + ", " + item.groupNo + "," + item.snsNo + ")")
								$(obj).find("#editbutton").attr("onclick", "rreplyEditForm(" + item.cmntNo + "," + item.snsNo + ")")


								return true;
							}
						});
					});
				} else {
					console.log("cmntCn : " + item.cmntCn);
					forCnt++;
					$("#loadDiv").load("sns/replyView.html", function(result) {
						var addedDiv = $("#Sns-reply").append(result);
						$(addedDiv).find(".replyGroup").each(function(index, obj) {
							if ($(obj).hasClass("set") == false) {
								$(obj).addClass("set");
								$(obj).addClass("numbering" + forCnt);
								$(obj).find("#rpGroupNo").val(item.groupNo);
								$(obj).find("#rpCmntNo").val(item.cmntNo);
								$(obj).find("#rpSnsNo").val(item.snsNo);
								$(obj).find("#cmntMem").text(item.cmntMem);
								$(obj).find("#cmntYmd").text(item.cmntYmd);
								$(obj).find("#cmntCn").text(item.cmntCn);
								$(obj).find("#cmntCn").attr('id', 'cmntCn' + item.cmntNo);
								$(obj).find(".name-commenter").attr('id', 'replyGroup' + item.cmntNo);
								$(obj).find("#replydel").attr('onclick', "rreplyDel(" + item.cmntNo + ", " + item.groupNo + "," + item.snsNo + ")");
								$(obj).find("#reInput").attr('onclick', 'ShowRrpInput(' + item.cmntNo + ',' + item.snsNo + ')');
								$(obj).find(".replyGroup").attr('id', 'replyGroup' + item.cmntNo);
								$(obj).find(".rrplyGroup").attr('id', 'rrplyGroup_' + item.cmntNo);
								$(obj).find("#editbutton").attr("onclick", "replyEditForm(" + item.cmntNo + "," + item.snsNo + ", " + item.groupNo + ")")
								return true;
							}

						})

					});
				}




				return true;
			});
		},
		error: function() {
			cosole.log(error)
		},
		async: false
	});
}



function ShowRrpInput(cmntNo, replySnsNo) {
	console.log("내가 띄운 답답답글의 번호 : " + cmntNo);
	var sessionID = "user1"; //아작스로 받아 올것
	$("#loadDiv").load("sns/rrpWrite.html", function(result) {
		var addedDiv = $("#rrplyGroup_" + cmntNo).append(result);
		$(addedDiv).find("#replyUserId").val(sessionID);
		$(addedDiv).find("#groupNo").val(cmntNo);
		$(addedDiv).find("#replySnsNo").val(replySnsNo);
		console.log("생성된 div ID : " + sessionID);
	});

}




//---------------------------------reply(insert)-----------------------------------
function insertReply() {
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
				alert("댓글 등록에 실패했습니다.");
			} else {
				alert("댓글 등록에 성공했습니다.");
				$("#Sns-reply").empty();
				SelectCmntlist(snsNo);
			}
		},
		error: function() {
			cosole.log(error)
		}
	});


}



function insertRrp() {
	let snsNo = $(".rrpBox #replySnsNo").val();
	let cmntMem = $(".rrpBox #replyUserId").val();
	let cmntCn = $(".rrpBox #replyCmntCn").val();

	let groupNo = $(".rrpBox #groupNo").val();

	console.log("groupNo : " + groupNo);
	console.log("snsNo : " + snsNo);
	console.log("cmntMem : " + cmntMem);
	console.log("cmntCn : " + cmntCn);


	if (cmntCn == " ") {
		alert("답글을 입력하세요");
		return;
	}


	$.ajax({
		url: "/insertReply",
		type: "POST",
		data: JSON.stringify({
			snsNo: snsNo,
			cmntMem: cmntMem,
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


function replyEditForm(rpCmntNo, rpSnsNo, rpGroupNo) {

	let button = event.currentTarget.parentNode;


	$('#cmntCn' + rpCmntNo).remove();
	$("#replyGroup" + rpCmntNo).append(`<textarea class="form-control" id="cmntCn" name="cmntCn" required></textarea>`);

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

	$('#rrpContent' + rrpCmntNo + ' #cmntCn').remove();

	$("#rrpContent" + rrpCmntNo).append(`<textarea class="form-control" id="cmntCn1" name="cmntCn" required></textarea>`);

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




	console.log

	$.ajax({
		url: "/rreplyEdit",
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
			url: "/deleteRreply",
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








