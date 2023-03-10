a = $("<select>").attr({
	"name": "userStts",
	"id": "key"
});
btn = $("<button>").attr({
	"class": "btn btn-primary",
	"id": "updateButton",

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

			$(".tableGroup")
				.find("#sort").append(`<option value="">전체</option>`)
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

function selectMemberList(dis) {
	console.log('ㅎㅇㅎㅇ')
	let formData = {}//$("#valueForm").serialize();
	//console.log(formData);
	if (dis == 1) {
		formData = $("#valueForm").serialize();


	} else {
		formData = $("#searchForm").serialize();
	}
	$.ajax({
		url: "/admin/selectMemberList",
		//method:""
		data: formData,
		//contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data) {
			console.log(data);
			$("#memberTable tbody").empty()
			$(data.list).each(function(index, mem) {
				let userId = JSON.stringify(mem.userId);
				let check = a.clone();
				check.find('[value=' + mem.userStts + ']').attr("selected", "selected")
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
					.append($("<td>").html(btn.clone().text('변경').attr("onclick", "updataStts(" + userId + ")")));

			})

			$(".pagination").append(`
				<li class="page-item disabled"><a class="page-link" id="previous"
									href="${data.page.first - 1}" tabindex="-1" aria-disabled="true">Previous</a></li>
									<li class="page-item disabled"><a class="page-link" id="previous"
									href="${data.page.first - 1}" tabindex="-1" aria-disabled="true">${data.page.startPage}</a></li>
			`)
			$(data.page).each(function(index, mem) {
				$(".pagination").append(`
				<li class="page-item"><a class="page-link" href="#">${mem.page}</a></li>
			`)
				$(".pagination").append(`
				<li class="page-item disabled"><a class="page-link" id="previous"
									href="${data.page.first - 1}" tabindex="-1" aria-disabled="true">${data.page.lastPage}</a></li>
				<li class="page-item disabled"><a class="page-link" id="next"
									href="${data.page.last - 1}" tabindex="-1" aria-disabled="true">Next</a></li>
			`)

			})




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





function updataStts(userId) {

	console.log(userId)
	let edit = event.currentTarget.parentNode;
	let userStts = $(edit).prev().find("select").val();

	console.log(userStts)



	if (userStts == 2) {
		let confirmMessage = confirm('정말 제명하시겠습니까?')

		if (confirmMessage)

			deleteMember(userId, userStts)


	} else {

		$.ajax({
			url: "/updateStts",
			type: "POST",
			data: {
				userStts: userStts,
				userId: userId,
			},
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			success: function(data) {
				console.log(data)
				if (data != null) {
					alert("수정이 완료 되었습니다.")
					$("#memberTable tbody").empty()
					selectMemberList()
				} else {
					alert("수정에 실패했습니다.")
				}


			}, error: function() {
				console.log(error)
			}


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
			console.log(data + "성공?")
			if (data != null) {
				alert("제명되었습니다..")
				$("#memberTable tbody").empty()
				selectMemberList()
			} else {
				alert("다시 시도해주세요")
			}


		}, error: function() {
			console.log(error)
		}


	});

}
