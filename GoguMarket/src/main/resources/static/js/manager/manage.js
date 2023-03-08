var asdsad = [];

$(document).ready(function() {
	alert("실행")

	let key = $("#key").val();

	console.log(key);


	$.ajax({
		url: "/keyValue",
		type: "GET",
		dataType: 'JSON',
		contentType: 'application/json; charset = utf-8',

		success: function(data) {
			$(data['codelist']).each(function(index, obj) {
				$(".tableGroup").find("#key").append(`<option id="${obj.commonDetailCode}" value="${obj.commonDetailCode}">${obj.commonNm}</option>`)
			});
		},
		error: function(data) {
			console.log(error);
		}
	});


});

