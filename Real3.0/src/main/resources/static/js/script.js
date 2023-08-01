function switchScreen() {
    var screen1 = document.getElementById('screen1');
    var screen2 = document.getElementById('screen2');

    screen1.style.display = 'none';
    screen2.style.display = 'block';
}

// 페이지가 로드되면 5초 후에 첫 번째 화면에서 두 번째 화면으로 전환
window.onload = function () {
    setTimeout(switchScreen, 2000); // 5000ms (5초) 후에 switchScreen 함수를 실행
}
