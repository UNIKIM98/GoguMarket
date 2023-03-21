
a = $("<select>").attr({
	name: "userStts",
	id: "key",
}); // 테이블에 변경항목 추가하기위한 태그 생성
btn = $("<button>").attr({
	class: "btn btn-primary",
	id: "updateButton",
}); // 테이블에 변경항목 추가하기위한 태그 생성



$(document).ready(function() { //페이지 로딩후 초기 코드,리스트 출력
	keyValue();
	selectMemberList();
});

function keyValue() { //공통코드 가져오기
	$.ajax({
		url: "/admin/keyValue",
		type: "GET",
		async: false,
		dataType: "JSON",
		contentType: "application/json; charset = utf-8",

		success: function(data) {
			console.log(data);
			$(".tableGroup").find("#sekey").append(`<option value="">전체</option>`); //전체 옵션 추가 (value = null)
			$(data["selist"]).each(function(index, obj) {
				$(".tableGroup")													 //
					.find("#sekey")
					.append(
						`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
					);
			});

			$(".tableGroup").find("#key").append(`<option value="">전체</option>`);	//전체 옵션 추가 (value = null)
			$(data["codelist"]).each(function(index, obj) {
				$(".tableGroup")
					.find("#key")
					.append(
						`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
					);
				a.append(
					`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
				);
			});

			$(".tableGroup").find("#sort").append(`<option value="">전체</option>`); //전체 옵션 추가 (value = null)
			$(data["searchlist"]).each(function(index, obj) {
				$(".tableGroup")
					.find("#sort")
					.append(
						`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
					);
			});

			

		},
		error: function(data) {
			console.log(error);
		},
	});
}

$(document).on("click", ".pageShiftIcon", function() {
	setPageJs($(this).attr("shiftType"));
});

var finalDis = 1;
var finalPage = 0;
var maxPage = 1;

function setPageJs(pageNum) { // 페이징 prev,next 함수
	if (pageNum == "prev") pageNum = finalPage - 1;
	if (pageNum == "next") pageNum = finalPage + 1;
	if (pageNum < 1) { // 첫번쨰 페이자 되면 break;
		pageNum = 1;
		return false;
	}
	if (pageNum > maxPage) { // 마지막 페이자 되면 break;
		pageNum = maxPage;
		return false;
	}
	selectMemberList(finalDis, pageNum);
}

function selectMemberList(dis, pageNum) {
	finalDis = dis;
	if (!pageNum) {
		pageNum = 1; // 초기값 지정
	}

	finalPage = pageNum;

	let formData = {}; //$("#valueForm").serialize();
	if (dis == 1) {
		formData = $("#valueForm").serialize(); //필터 출력 form
	} else {
		formData = $("#searchForm").serialize(); //검색 출력 form
	}
	formData += "&userNowPage=" + pageNum; //url에 현재 페이지 정보를 추가
	$.ajax({
		url: "/admin/selectMemberList",
		//method:""
		data: formData,
		//contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data) {
			console.log(data['list'].length);
			
			if(data['list'].length == 0) { // 회원 정보가 없을경우
				$("#memberTable tbody").load("/admin/noMember.html")
			}
			
				
			$("#memberTable tbody").empty(); // 기존 리스트를 지우고 재출력
			$(".pageArrow").empty(); //페이징 번호도 새로 출력
			$(data.list).each(function(index, mem) {
				let userId = JSON.stringify(mem.userId); //json 문장열로 변환
				let check = a.clone(); //select 태그를 복사
				check.find("[value=" + mem.userStts + "]").attr("selected", "selected"); //셀렉트를 선택후 새로고침해도 변경된 셀렉트 그대로
				$("#memberTable tbody")
					.append("<tr>")
					.append($("<td>").text(index + 1))
					.append(
						$("<td>").attr("class", "dropdown event-dropdown")
							.append(` <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
										      ${mem.userId}
										      <span class="caret"></span>
										    </a>
										    <ul class="dropdown-menu">
										      <li><a href="/admin/alarm/${mem.userId}">알림 보내기</a></li>
										    </ul>
										 		`)
					)
					.append($("<td>").text(mem.userNm))
					.append($("<td>").text(mem.nickNm))
					.append($("<td>").text(mem.addr))
					.append($("<td>").text(mem.mblTelno))
					.append($("<td>").text(mem.eml))
					.append($("<td>").html(check))
					.append(
						$("<td>").html(btn.clone().text("변경").attr("onclick", "updataStts(" + userId + ")").attr("style","font-size:small;")
						)
					);
			});
			
			maxPage = data.page["endPage"];

			$(".pagination").append( //prev 옵션 append
				'<li class="page-item prevBtn"><a class="page-link pageShiftIcon " shiftType="prev" aria-disabled="true">Previous</a></li>'
			);

			for (i = 0; i < parseInt(data.page["endPage"]); i++) {//페이징 번호 append
				$(".pagination").append(
					' <li class="page-item page_' +
					(i + 1) +
					'"><a class="page-link" href="#" onClick="setPageJs(' +
					(i + 1) +
					')"> ' +
					(i + 1) +
					"</a></li>"
				);
			}
			$(".pagination").append( //next 옵션 append
				'<li class="page-item nextBtn"><a class="page-link pageShiftIcon" shiftType="next" aria-disabled="true">Next</a></li>'
			);

			if (pageNum == 1) $(".prevBtn").addClass("disabled");
			else $(".prevBtn").removeClass("disabled");
			if (pageNum == maxPage) $(".nextBtn").addClass("disabled");
			else $(".nextBtn").removeClass("disabled");
			$(".page_" + finalPage).addClass("active");
		},
		error: function(error) {
			console.log(error);
		},
	});
}






function updataStts(userId) { //삭제 선택및 상태 수정 함수
	console.log(userId);
	let edit = event.currentTarget.parentNode;
	
	let userStts = $(edit).prev().find("select").val(); //선택한 회원 상태를 저장

	console.log(userStts);

	if (userStts == 2) { // 회원 삭제
		let confirmMessage = confirm("정말 제명하시겠습니까?");

		if (confirmMessage) deleteMember(userId, userStts);
	} else { //회원 업데이트
		
		confirmMessage = confirm("정말 수정하시겠습니까?");
		
		if(confirmMessage) 
		
		
		
		$.ajax({
			url: "/admin/updateStts",
			type: "POST",
			data: {
				userStts: userStts,
				userId: userId,
			},
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				console.log(data);
				if (data != null) {
					alert("수정이 완료 되었습니다.");
					$("#memberTable tbody").empty();
					selectMemberList();
				} else {
					alert("수정에 실패했습니다.");
				}
			},
			error: function() {
				console.log(error);
			},
		});
	}
}

function deleteMember(userId, userStts) {
	$.ajax({
		url: "/deleteMember",
		type: "POST",
		data: {
			userId: userId,
		},
		contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		success: function(data) {
			console.log(data + "성공?");
			if (data != null) {
				alert("제명되었습니다..");
				$("#memberTable tbody").empty();
				selectMemberList();
			} else {
				alert("다시 시도해주세요");
			}
		},
		error: function() {
			console.log(error);
		},
	});
}
