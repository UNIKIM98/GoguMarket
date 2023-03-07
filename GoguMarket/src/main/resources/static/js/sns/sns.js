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





//---------------- 게시글 list-------------------------


//------------------ 개인 게시글-------------------------

function snsModal(id) {
	//활상화
	Sns.style.display = "block"; //개인 게시글 창 활성화

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
								$(obj).find("#rrpGroupNo").val(item.groupNo);
								$(obj).find("#rrpCmntNo").val(item.cmntNo);
								$(obj).find("#rrpCmntMem").text(item.cmntMnm);
								$(obj).find("#rrpCmntYmd").text(item.cmntYmd);
								$(obj).find("#rrpCmntCn").text(item.cmntCn);
								$(obj).find("#delbutton").attr("onclick", "rreplyDel(" + item.cmntNo + ", " + item.groupNo + ")")


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
								$(obj).addClass("numbering_" + forCnt);
								$(obj).find("#cmntMem").text(item.cmntMem);
								$(obj).find("#cmntYmd").text(item.cmntYmd);
								$(obj).find("#cmntCn").text(item.cmntCn);
								$(obj).find("#reInput").attr('onclick', 'ShowRrpInput(' + item.cmntNo + ',' + item.snsNo + ')');
								$(obj).find(".rrplyGroup").attr('id', 'rrplyGroup_' + item.cmntNo);
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
				alert("게시물 등록에 실패했습니다.");
			} else {
				alert("게시물 등록에 성공했습니다.");
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

function replyDel(rrpCmntNo, rrpGroupNo) {

	$.ajax({
		url: "/deleteReply",
		type: "POST",
		data: JSON.stringify({
			cmntNo: rrpCmntNo,
			groupNo: rrpGroupNo,

		}),

		contentType: "application/json",
		success: function(data) {
			console.loc("sec");
		}, error: function() {
			console.log(error)
		}
	});

}


function rreplyDel(rrpCmntNo, rrpGroupNo) {
	$.ajax({
		url: "/deleteRreply",
		type: "POST",
		data: JSON.stringify({
			cmntNo: rrpCmntNo,
			groupNo: rrpGroupNo,
		}),
		contentType: "application/json",

		success: function(data) {

		},
		error: function() {
			cosole.log(error)
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








