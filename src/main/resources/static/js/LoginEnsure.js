function LoginEnsure() {
    const username = $(".username");
    const password = $(".password");
    if (username.val() === "") {
        alert("用户名为空");
        return false;
    } else if (password.val() === "") {
        alert("密码为空");
        return false;
    }
    return true;
}