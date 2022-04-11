    // 쿠키 삭제
    const delCookie = function delCookie_by_name(name){
    let date = new Date();
    date.setDate(date.getDate() - 100);
    let Cookie = `${name}=;Expires=${date.toUTCString()}`
    document.cookie = Cookie;
}
    const logout = () => {
    delCookie("Authorization");
    location.href="/"
}