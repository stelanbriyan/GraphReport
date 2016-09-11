/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(function () {
    $('.item-table').hide();
    $.ajax({
        type: "GET",
        url: "v1/web/reps",
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
    hideAllChart();
    $('#area-chart').show();
    $('.selectpicker').selectpicker({
        style: 'btn-info',
        size: 12
    });

    $('#search').on('click', function () {
        $('.item-table').hide();
        var repList = $('#list').val();
        var monthList = $('#monthList').val();
        var jsonData = {
            "months": monthList,
            "reps": repList
        };
        var chartType = $('#chart-type').val();
        if (chartType === 'Area Chart') {
            hideAllChart();
            $('#area-chart').show();
        } else if (chartType === 'Pie Chart') {
            hideAllChart();
            $('#pie-chart').show();
        } else if (chartType === 'Bar Chart') {
            hideAllChart();
            $('#bar-chart').show();
        } else if (chartType === 'Column Chart') {
            hideAllChart();
            $('#column-chart').show();
        } else if (chartType === 'Line Chart') {
            hideAllChart();
            $('#line-chart').show();
        }


        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "v1/web/repreport/2016/get",
            data: JSON.stringify(jsonData),
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                loadAreaChart(data.report_1);
                loadPieChart(data.report_2);
                loadBarChart(data.report_3);
                loadColumnChart(data.report_4);
                loadLineChart(data.report_5);
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

function loadPieChart(json) {
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
                        dataPoints: json
                    }
                ]
            });

    chart2.render();
}

function loadBarChart(json) {
    var chart3 = new CanvasJS.Chart("chartContainer3",
            {
                title: {
                    text: ""
                },
                data: [
                    {
                        type: "bar",
                        dataPoints: json
                    }
                ]
            });
    chart3.render();
}
function hideAllChart() {
    $('#area-chart, #pie-chart, #bar-chart, #column-chart, #line-chart').hide();
}
function loadColumnChart(json) {
    var chart4 = new CanvasJS.Chart("chartContainer4", {
        title: {
            text: ""
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
                chart4.render();
            }
        }
    });
    chart4.render();
}

function loadLineChart(json) {
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
                data: json,
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
}