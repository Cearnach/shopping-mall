function login() {
    var username = document.forms[0].username.value;
    var password = document.forms[0].password.value;
    if (username == null || username.trim().length === 0 || password == null || password.trim().length === 0) {
        alert("用户名或者密码不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/auth/user/login",
        data: "username=" + username + "&password=" + password,
        success: function (result) {
            if (result.success) {
                location.href = "admin/index.html";
            } else {
                alert(result.message);
            }
        }
    });
}