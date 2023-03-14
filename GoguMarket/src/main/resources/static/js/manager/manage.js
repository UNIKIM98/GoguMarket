a = $("<select>").attr({
  name: "userStts",
  id: "key",
});
btn = $("<button>").attr({
  class: "btn btn-primary",
  id: "updateButton",
});

$(document).ready(function () {
  keyValue();
  selectMemberList();
});

function keyValue() {
  $.ajax({
    url: "/admin/keyValue",
    type: "GET",
    async: false,
    dataType: "JSON",
    contentType: "application/json; charset = utf-8",

    success: function (data) {
      console.log(data);
      $(".tableGroup").find("#sekey").append(`<option value="">전체</option>`);
      $(data["selist"]).each(function (index, obj) {
        $(".tableGroup")
          .find("#sekey")
          .append(
            `<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
          );
      });

      $(".tableGroup").find("#key").append(`<option value="">전체</option>`);
      $(data["codelist"]).each(function (index, obj) {
        $(".tableGroup")
          .find("#key")
          .append(
            `<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
          );
        a.append(
          `<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
        );
      });

      $(".tableGroup").find("#sort").append(`<option value="">전체</option>`);
      $(data["searchlist"]).each(function (index, obj) {
        $(".tableGroup")
          .find("#sort")
          .append(
            `<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
          );
      });
    },
    error: function (data) {
      console.log(error);
    },
  });
}

$(document).on("click", ".pageShiftIcon", function () {
  setPageJs($(this).attr("shiftType"));
});

var finalDis = 1;
var finalPage = 0;
var maxPage = 1;

function setPageJs(pageNum) {
  if (pageNum == "prev") pageNum = finalPage - 1;
  if (pageNum == "next") pageNum = finalPage + 1;
  if (pageNum < 1) {
    pageNum = 1;
    return false;
  }
  if (pageNum > maxPage) {
    pageNum = maxPage;
    return false;
  }
  console.log("clickCall : " + pageNum);
  selectMemberList(finalDis, pageNum);
}

function selectMemberList(dis, pageNum) {
  finalDis = dis;
  if (!pageNum) {
    console.log("is null to set");
    pageNum = 1;
  }

  finalPage = pageNum;

  let formData = {}; //$("#valueForm").serialize();
  if (dis == 1) {
    formData = $("#valueForm").serialize();
  } else {
    formData = $("#searchForm").serialize();
  }
  formData += "&userNowPage=" + pageNum;
  console.log("테스트 " + formData);
  $.ajax({
    url: "/admin/selectMemberList",
    //method:""
    data: formData,
    //contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    success: function (data) {
      console.log(data);

      $("#memberTable tbody").empty();
      $(".pageArrow").empty();
      $(data.list).each(function (index, mem) {
        let userId = JSON.stringify(mem.userId);
        let check = a.clone();
        check.find("[value=" + mem.userStts + "]").attr("selected", "selected");
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
            $("<td>").html(
              btn
                .clone()
                .text("변경")
                .attr("onclick", "updataStts(" + userId + ")")
            )
          );
      });

      console.log("마지막 페이지 : " + data.page["endPage"]);
      console.log("레코드 카운트 : " + data.page["totalRecord"]);
      maxPage = data.page["endPage"];

      $(".pagination").append(
        '<li class="page-item prevBtn"><a class="page-link pageShiftIcon " shiftType="prev" aria-disabled="true">Previous</a></li>'
      );

      for (i = 0; i < parseInt(data.page["endPage"]); i++) {
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
      $(".pagination").append(
        '<li class="page-item nextBtn"><a class="page-link pageShiftIcon" shiftType="next" aria-disabled="true">Next</a></li>'
      );

      if (pageNum == 1) $(".prevBtn").addClass("disabled");
      else $(".prevBtn").removeClass("disabled");
      if (pageNum == maxPage) $(".nextBtn").addClass("disabled");
      else $(".nextBtn").removeClass("disabled");
      $(".page_" + finalPage).addClass("active");

      /*.append(`<td>${index}</td>`)
				  .append(`<td>${mem.userId}</td>`)
				  .append(`<td>${mem.userNm}</td>`)
				  .append(`<td>${mem.nickNm}</td>`)
				  .append(`<td>${mem.addr}</td>`)
				  .append(`<td>${mem.mblTelno}</td>`)
				  .append(`<td>${mem.eml}</td>`)
				  .append(`<td>${a.clone().html()}</td>`) // 상태 출력 및 변경 옵션
				  .append(`<td>${mem.userStts}</td>`); // 상태 변경 업데이트 (제명 / delete)
					  */
    },
    error: function (error) {
      console.log(error);
    },
  });
}

/*
  // dropdown 메뉴가 보이기 직전에 호출되는 이벤트
  $('.event-dropdown').on('show.bs.dropdown', function () {
    consoleArea.log("메뉴가 열리기 전 이벤트!");
  });
  // dropdown 메뉴가 보이기 직후에 호출되는 이벤트
  $('.event-dropdown').on('shown.bs.dropdown', function () {
    consoleArea.log("메뉴가 열린 후 이벤트!");
  });
  // dropdown 메뉴가 사라지기 직전에 호출되는 이벤트
  $('.event-dropdown').on('hide.bs.dropdown', function () {
    consoleArea.log("메뉴가 닫히기 전 이벤트!");
  });
  // dropdown 메뉴가 사라진 직후에 호출되는 이벤트
  $('.event-dropdown').on('hidden.bs.dropdown	', function () {
    consoleArea.log("메뉴가 닫힌 후 이벤트!");
  });
 */

function showInFo() {
  // dropdown 메뉴가 보이기 직전에 호출되는 이벤트
}

function updataStts(userId) {
  console.log(userId);
  let edit = event.currentTarget.parentNode;
  let userStts = $(edit).prev().find("select").val();

  console.log(userStts);

  if (userStts == 2) {
    let confirmMessage = confirm("정말 제명하시겠습니까?");

    if (confirmMessage) deleteMember(userId, userStts);
  } else {
    $.ajax({
      url: "/updateStts",
      type: "POST",
      data: {
        userStts: userStts,
        userId: userId,
      },
      contentType: "application/x-www-form-urlencoded; charset=UTF-8",
      success: function (data) {
        console.log(data);
        if (data != null) {
          alert("수정이 완료 되었습니다.");
          $("#memberTable tbody").empty();
          selectMemberList();
        } else {
          alert("수정에 실패했습니다.");
        }
      },
      error: function () {
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
    success: function (data) {
      console.log(data + "성공?");
      if (data != null) {
        alert("제명되었습니다..");
        $("#memberTable tbody").empty();
        selectMemberList();
      } else {
        alert("다시 시도해주세요");
      }
    },
    error: function () {
      console.log(error);
    },
  });
}
