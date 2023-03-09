a = $("<select>");
btn = $("<button>").attr({
	"class": "btn btn-primary",
	"id": "updateButton",
	"onclick": "updataStts(check)"
});

$(document).ready(function() {
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

		success: function(data) {
			console.log(data);
				$(".tableGroup")
					.find("#sekey").append(`<option value="">전체</option>`)
			$(data["selist"]).each(function(index, obj) {
				$(".tableGroup")
					.find("#sekey")
					.append(
						`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`
					);

			});

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

function selectMemberList() {
	console.log('ㅎㅇㅎㅇ')
	var formData = $("#valueForm").serialize();

	console.log(formData);

	$.ajax({
		url: "/admin/selectMemberList",
		//method:""
		data: formData,
		//contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data) {
			console.log(data);
			$("#memberTable tbody").empty()
			$(data).each(function(index, mem) {
			let check = a.clone();
				check.find('[value='+mem.userStts+']').attr("selected","selected")
				$("#memberTable tbody")
					.append("<tr>")
					.append($("<td>").text(index + 1))
					.append($("<td>").text(mem.userId))
					.append($("<td>").text(mem.userNm))
					.append($("<td>").text(mem.nickNm))
					.append($("<td>").text(mem.addr))
					.append($("<td>").text(mem.mblTelno))
					.append($("<td>").text(mem.eml))
					.append($("<td>").html(check))
					.append($("<td>").html(btn.clone().text('변경')));

			});



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
		error: function(error) {
			console.log(error);
		},
	});
}
