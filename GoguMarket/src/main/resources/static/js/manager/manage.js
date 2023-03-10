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
	let formData = $("#valueForm").serialize();

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



function Search() {


	let search = $("#searchForm").serialize(); // 검색키

	console.log(search);

	$.ajax({
		url: "/search",
		type: "get",
		data: search,
		dataType: "JSON",

		success: function(data) {
			console.log(data)
		},error:function(error){
			console.log(error)
		}
	})



}



function updataStts(userId) {

	console.log(userId)
	let edit = event.currentTarget.parentNode;
	let userStts = $(edit).prev().find("select").val();

	console.log(userStts)



	if (userStts == 2) {
		deleteMember(userId, userStts)
		console.log('삭제')

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
	console.log('gdgd')
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
