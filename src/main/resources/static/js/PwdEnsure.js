function registerEnsure() {
    const username = $(".username");
    const password = $(".password");
    const repassword = $(".repassword");
    if (username.val() === "") {
        alert("用户名为空");
        return false;
    } else if (password.val() === "") {
        repassword.val("");
        alert("密码为空");
        return false;
    } else if (password.val() !== repassword.val()) {
        repassword.val("");
        alert("密码不一致");
        return false;
    }
    return true;
}