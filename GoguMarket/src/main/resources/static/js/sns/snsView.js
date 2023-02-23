Date.prototype.yyyymmdd = function() { //객체의 모든 타입은 prototype사용해 메소드 만들수 있따.
	let y = this.getFullYear();
	let m = this.getMonth() + 1; //월은 0부터 시작
	let d = this.getDate();
	let ymd = y + '-' + ('0' + m).slice(-2) + '-' + ('0' + d).slice(-2);
	return ymd;
}
let today = new Date();

const showFields = {
	noticeId: "공지번호",
	noticeTitle: "제목",
	noticeDate: "작성일자",
	noticeHit: "조회수",
	noticeWriter: "작성자"

}


function makeTr(data) { // 리스트 출력

	const parent = document.querySelector('#notice table tbody');

	for (const el of data) {

		let tr = document.createElement('tr')


		for (const field in showFields) {
			let td = document.createElement('td')
			td.setAttribute('id', field)
			td.innerText = el[field]
			tr.append(td);


		}
		tr.addEventListener('click', noticeSelect)
		console.log(tr)
		parent.append(tr);

	}
}

fetch('noticeListAjax.do')
	.then((result) => result.json()) // stream -> object.
	.then((data) => {
		console.log(data);
		//table형식.
		makeTr(data)

		let trs = document.querySelectorAll('#notice tbody tr');
		for (const trElem of trs) {
			console.log()
			trElem.setAttribute('id', 'notice_' + trElem.firstChild.innerText)


		}
	})
	.catch(function(err) {
		console.log(err);
	})

function noticeSelect() {  //선택글 상세보기
	let id = this.firstChild.innerText
	console.log(id)
	document.querySelector('#frm input').value = id;
	frm.submit();

}


function noticeEdit(str) {
	console.log('ㅎㅇ')
	if (str == 'E') {
		console.log('이동 jsp')
		frm.action ="noticeEditForm.do";
	} else {
		let yn = confirm("작성글을 삭제하겠나?");
		if (yn) {

			frm.action = "noticeDelete.do";

		} else {
			return false;
		}
	}

	frm.submit();
}






