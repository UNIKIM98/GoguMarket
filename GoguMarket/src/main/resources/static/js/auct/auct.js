/**
 * 경매 메인페이지
 */





/* JS타이머입니다 안쓸듯ㅋㅋ
const remainTime = document.querySelector("#remain-time");
function diffDay(){
    const ddln = new Date("2023-12-25");
    const todayTime = new Date();

    const diff = ddln - todayTime;

    const diffDay = Math.floor(diff / (1000*60*60*24));
    const diffHour = Math.floor(diff / ((1000*60*60)) % 24);
    const diffMin = Math.floor(diff / ((1000*60)) % 60);
    const diffSec = Math.floor(diff / 1000 % 60);

    remainTime.innerText = `${diffDay}일 ${diffHour}시간 ${diffMin}분 ${diffSec}초`;
}

diffDay();
setInterval(diffDay, 1000);
*/


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