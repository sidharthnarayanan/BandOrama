function eInput() {
    var equipmentJson = {
        equipment: $("#userMouthpiece").find(":selected").text(),
        brand: $("#musicBrand").val(),
        instrument: $("#userInstrument").find(":selected").text(),
    };
    $.ajax({
        url:"api/bandServer/processEquipment",
        type:"POST",
        data:JSON.stringify(equipmentJson),
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data) {
            window.location.href = "dashboard.html";
        }
    })
}

function displayEquipment() {
    $.ajax({
        url:"api/bandServer/getEquipment",
        type:"GET",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data) {
            $("#equipmentInput").val(data.equipment);
            $("#musicBrand").val(data.brand);
            $("#userInstrument").val(data.instrument);
        }
    })
}

function displayMouthpieceInDropdowm(instrument) {
    $.ajax({
        url:"api/bandServer/getMouthpieceInfo?instrument=" + instrument,
        type:"GET",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data) {
           for (var i = 0; i<data.length; i++){
              $("#Mouthpiece" + i).text(data[i]);
           }
        }
    })
}



$(document).ready(function(){
    $('#userInstrument').change(function() {
        displayMouthpieceInDropdowm($(this).val());
    });
    //calling displayEquipment because if the user already has equipment stored, then you show it in the equipment page
    displayEquipment();
})