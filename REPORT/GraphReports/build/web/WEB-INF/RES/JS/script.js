/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $.ajax({
        type: "POST",
        url: "v1/web/type-name",
        success: function (data, textStatus, jqXHR) {
            for (var property in data) {
                var value = "'" + data[property] + "'";
                $('#typelist').append('<option value="' + "'" + data[property].trim() + "'" + '">' + data[property] + "</option>");
//                alert(data[property]);
            }
            $('#typelist').selectpicker({
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
        var typeList = $('#typelist').val();
        var monthList = $('#monthList').val();
        var data = {
            "months": monthList,
            "types": typeList
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "v1/web/report/2016/get",
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                loadChart(data);
                loadPieChartOne(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    });

});

function loadPieChartOne(json) {
    var chart2 = new CanvasJS.Chart("chartContainer2",
            {
                theme: "theme2",
                title: {
                    text: ""
                },
                data: [
                    {
                        type: "pie",
                        showInLegend: true,
                        toolTipContent: "{y} - #percent %",
                        yValueFormatString: "#0.#,,. Million",
                        legendText: "{indexLabel}",
                        dataPoints: json.report_2
                    }
                ]
            });
    var chart3 = new CanvasJS.Chart("chartContainer3",
            {
                title: {
                    text: ""
                },
                data: [
                    {
                        type: "bar",
                        dataPoints: json.report_3
                    }
                ]
            });
    chart3.render();
    chart2.render();
}
function loadChart(json) {

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
        data: json.report_1,
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


    var chart4 = new CanvasJS.Chart("chartContainer4", {
        title: {
            text: ""
        },
        data: json.report_4,
        legend: {
            cursor: "pointer",
            itemclick: function (e) {
                if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                    e.dataSeries.visible = false;
                } else {
                    e.dataSeries.visible = true;
                }
                chart4.render();
            }
        }
    });

    var chart = new CanvasJS.Chart("chartContainer5",
            {
                zoomEnabled: false,
                animationEnabled: true,
                axisY2: {
                    interval: 200000,
                    interlacedColor: "#F5F5F5",
                    gridColor: "#D7D7D7",
                    tickColor: "#D7D7D7"
                },
                theme: "theme2",
                toolTip: {
                    shared: true
                },
                legend: {
                    verticalAlign: "bottom",
                    horizontalAlign: "center",
                    fontSize: 15,
                    fontFamily: "Lucida Sans Unicode"
                },
                data: json.report_5,
                legend: {
                    cursor: "pointer",
                    itemclick: function (e) {
                        if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                            e.dataSeries.visible = false;
                        } else {
                            e.dataSeries.visible = true;
                        }
                        chart.render();
                    }
                }
            });

    chart.render();

    chart1.render();
    chart4.render();
}





//var data1 = [
//    {
//        type: "stackedArea",
//        name: "cars",
//        showInLegend: "true",
//        dataPoints: [
//            {"label": "Jan", "y": 100},
//            {"label": "Feb", "y": 500},
//            {"label": "March", "y": 400},
//            {"label": "April", "y": 600},
//            {"label": "May", "y": 400},
//            {"label": "June", "y": 400},
//            {"label": "July", "y": 300},
//            {"label": "Aug", "y": 400},
//            {"label": "Sep", "y": 140},
//            {"label": "Oct", "y": 700}
//        ]
//    }
//];

//    var data2 = [
//        {y: 4181563, indexLabel: "PlayStation 3"},
//        {y: 2175498, indexLabel: "Wii"},
//        {y: 3125844, indexLabel: "Xbox 360"},
//        {y: 1176121, indexLabel: "Nintendo DS"},
//        {y: 1727161, indexLabel: "PSP"},
//        {y: 4303364, indexLabel: "Nintendo 3DS"},
//        {y: 1717786, indexLabel: "PS Vita"}
//    ];