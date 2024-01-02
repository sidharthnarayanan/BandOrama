function changePassword() {
    var newPasswordInfo = {
        username: $("#uName").val(),
        password: $("#nPswd").val(),
    };
    $.ajax({
        url:"api/bandServer/newpassword",
        type:"POST",
        data:JSON.stringify(newPasswordInfo),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data){
            alert("Successfully changed password");
            window.location.href = "Login.html";
        }
    })
}