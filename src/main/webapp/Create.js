function Create() {
    var newUserInfo = {
        username: $("#nUname").val(),
        password: $("#nPswd").val(),
    };
    $.ajax({
        url:"api/bandServer/newuser",
        type:"POST",
        data:JSON.stringify(newUserInfo),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data){
            window.location.href = "Login.html";
        }
    })
}