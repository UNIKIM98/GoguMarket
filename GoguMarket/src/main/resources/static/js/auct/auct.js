/**
 * 경매 메인페이지
 */


function auctDelete() {
    console.log("삭제기능~")
}

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






