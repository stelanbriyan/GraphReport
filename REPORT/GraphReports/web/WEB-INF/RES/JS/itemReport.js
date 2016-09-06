/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $.ajax({
        type: "GET",
        url: "v1/web/items",
        success: function (data, textStatus, jqXHR) {
            for (var property in data) {
                var value = "'" + data[property] + "'";
                $('#list').append('<option value="' + "'" + data[property].trim() + "'" + '">' + data[property] + "</option>");
            }
            $('#list').selectpicker({
                style: 'btn-info',
                size: 12
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(errorThrown);
        }
    });


    $('.selectpicker').selectpicker({
        style: 'btn-info',
        size: 12
    });

    $('#search').on('click', function () {
        var itemList = $('#list').val();
        var monthList = $('#monthList').val();
        var jsonData = {
            "months": monthList,
            "items": itemList
        };

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "v1/web/itemreport/2016/get",
            data: JSON.stringify(jsonData),
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                loadAreaChart(data.report_1);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    });
});

function loadAreaChart(json) {
    var chart1 = new CanvasJS.Chart("chartContainer1", {
        title: {
            text: "",
            margin: 15
        },
        toolTip: {
            shared: true
        },
        axisX: {
            valueFormatString: "MMM",
            interval: 1,
            intervalType: "month"
        },
        axisY: {
            interval: 500000
        },
        legend: {
            verticalAlign: "bottom",
            horizontalAlign: "center"
        },
        data: json,
        legend: {
            cursor: "pointer",
            itemclick: function (e) {
                if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                } else {
                    e.dataSeries.visible = true;
                }
                chart1.render();
            }
        }
    });
    chart1.render();
}

function loadPieChart(json){
    
}

function loadBarChart(json){
    
}

function loadColumnChart(json){
    
}

function loadLineChart(json){
    
}