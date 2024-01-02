function getInstrumentInfo(instrument) {
    $.ajax({
        url:"api/bandServer/getInstrumentInfo?instrument=" + instrument,
        type:"GET",
        contentType:"application/json; charset=utf-8",
        dataType:"json",
        success: function(data) {
            for (var i = 0; i < data.length; i++) {
                $("#instrumentTable tr:last").after("<tr><td>"+data[i].username+"</td><td>"+data[i].equipment.equipment+"</td><td>"+data[i].equipment.brand+"</td></tr>")
            }
        }
    })
}


$(document).ready(function(){
    const searchParams = new URLSearchParams(window.location.search);
    getInstrumentInfo(searchParams.get('instrument'));
})