function displayEquipment() {
    $.ajax({
        url:"api/bandServer/getEquipment",
        type:"GET",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data) {
            $("#dEquipID").text(data.equipment);
            $("#dBrandID").text(data.brand);
            $("dInstrumentID").text(data.instrument);
        }
    })
}

function displayInstruments() {
    $.ajax({
        url:"api/bandServer/getInstruments",
        type:"GET",
        dataType:"json",
        success: function(data) {
            let url = "instrumentInfo.html?instrument=";
            //console.log(JSON.stringify(data));
            for (var i = 0; i < data.length; i++) {
                $("#Label" + i).text(data[i].instrument);
                $("#Img" + i).attr("src", data[i].imageURL);
                $("#href" + i).attr("href", url + data[i].instrument);
                //console.log(data[i].imageURL);
            }
        }
    })
}

$(document).ready(function(){
    displayEquipment();
    displayInstruments();
});