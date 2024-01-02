function login() {
    var loginJson = {
        username: $("#uName").val(),
        password: $("#pswd").val(),
    };
    $.ajax({
        url:"api/bandServer/CheckLogin",
        type:"POST",
        data:JSON.stringify(loginJson),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data, status) {
            if (data.status)
                window.location.href = "dashboard.html";
            else 
                alert("Try Again");
        }
    })
}