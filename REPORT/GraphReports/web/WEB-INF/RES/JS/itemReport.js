/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var table = null;
var tableData = null;
$(function () {
    $('.item-table').hide();
    $.ajax({
        type: "GET",
        url: "v1/web/items",
        success: function (data, textStatus, jqXHR) {
            for (var property in data) {
                var value = "'" + data[property] + "'";
                $('#list').append('<option value="' + "'" + data[property].trim() + "'" + '">' + data[property].trim() + "</option>");
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
    $.ajax({
        type: "POST",
        url: "v1/web/years",
        success: function (data, textStatus, jqXHR) {
            for (var i = 0; i < data.length; i++) {
                $('#yearList').append('<option value="' + data[i] + '">' + data[i] + '</option>');
            }
            $('#yearList').selectpicker({
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
    hideAllChart();
    $('#area-chart').show();
    $('#search').on('click', function () {
        $('.item-table').hide();
        var itemList = $('#list').val();
        var monthList = $('#monthList').val();
        var jsonData = {
            "months": monthList,
            "items": itemList
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
            url: "v1/web/itemreport/2016/get",
            data: JSON.stringify(jsonData),
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                loadAreaChart(data);
                loadPieChart(data);
                loadBarChart(data);
                loadColumnChart(data);
                loadLineChart(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(errorThrown);
            }
        });
    });

    table = $('#table-id').DataTable({
        pagingType: "full_numbers",
        columnDefs: [{
                "targets": [0, 4],
                "searchable": true
            }]
    });
});

function loadAreaChart(json) {

    var chart1Data = [];

    for (var i = 0; i < json.report_1.length; i++) {
        var x = {
            type: "stackedArea",
            name: json.report_1[i].name,
            showInLegend: "true",
            dataPoints: json.report_1[i].dataPoints,
            cursor: "pointer",
            click: function (e) {
                var inputData = {
                    "item": e.dataSeries.name,
                    "month": e.dataPoint.label
                };
                var year = $('#yearList').val();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "v1/web/sales/" + year + "/read",
                    data: JSON.stringify(inputData),
                    dataType: 'json',
                    success: function (jsonData, textStatus, jqXHR) {
                        tableData = jsonData;
                        loadItemTable2(e, jsonData);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            }
        };
        chart1Data.push(x);
    }

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
        data: chart1Data,
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
                        yValueFormatString: "0.00 LKR",
//                        yValueFormatString: "#0.#,,. Million",
                        legendText: "{indexLabel}",
                        dataPoints: json.report_2,
                        cursor: "pointer",
                        click: function (e) {
                            var inputData = {
                                "item": e.dataPoint.indexLabel,
                                "month": $('#monthList').val()
                            };
                            var year = $('#yearList').val();
                            $.ajax({
                                type: "POST",
                                contentType: "application/json",
                                url: "v1/web/sales/" + year + "/read",
                                data: JSON.stringify(inputData),
                                dataType: 'json',
                                success: function (jsonData, textStatus, jqXHR) {
                                    tableData = jsonData;
                                    loadItemTable(e, jsonData);
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
                            });
                        }
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
                        dataPoints: json.report_3,
                        cursor: "pointer",
                        click: function (e) {
                            var inputData = {
                                "item": e.dataPoint.label,
                                "month": $('#monthList').val()
                            };
                            var year = $('#yearList').val();
                            $.ajax({
                                type: "POST",
                                contentType: "application/json",
                                url: "v1/web/sales/" + year + "/read",
                                data: JSON.stringify(inputData),
                                dataType: 'json',
                                success: function (jsonData, textStatus, jqXHR) {
                                    tableData = jsonData;
                                    loadItemTable(e, jsonData);
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    console.log(errorThrown);
                                }
                            });
                        }
                    }
                ]
            });
    chart3.render();
}

function loadColumnChart(json) {

    var chart4Data = [];

    for (var i = 0; i < json.report_4.length; i++) {
        var x = {
            type: "column",
            name: json.report_4[i].name,
            showInLegend: "true",
            dataPoints: json.report_4[i].dataPoints,
            cursor: "pointer",
            click: function (e) {
                var inputData = {
                    "item": e.dataSeries.name,
                    "month": e.dataPoint.label
                };
                var year = $('#yearList').val();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "v1/web/sales/" + year + "/read",
                    data: JSON.stringify(inputData),
                    dataType: 'json',
                    success: function (jsonData, textStatus, jqXHR) {
                        tableData = jsonData;
                        loadItemTable2(e, jsonData);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            }
        };
        chart4Data.push(x);
    }

    var chart4 = new CanvasJS.Chart("chartContainer4", {
        title: {
            text: ""
        },
        data: chart4Data,
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

    var chart5Data = [];

    for (var i = 0; i < json.report_5.length; i++) {
        var x = {
            type: "line",
            name: json.report_5[i].name,
            showInLegend: "true",
            dataPoints: json.report_5[i].dataPoints,
            cursor: "pointer",
            click: function (e) {
                var inputData = {
                    "item": e.dataSeries.name,
                    "month": e.dataPoint.label
                };
                var year = $('#yearList').val();
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "v1/web/sales/" + year + "/read",
                    data: JSON.stringify(inputData),
                    dataType: 'json',
                    success: function (jsonData, textStatus, jqXHR) {
                        tableData = jsonData;
                        loadItemTable2(e, jsonData);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        console.log(errorThrown);
                    }
                });
            }
        };
        chart5Data.push(x);
    }

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
                data: chart5Data,
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

function hideAllChart() {
    $('#area-chart, #pie-chart, #bar-chart, #column-chart, #line-chart').hide();
}

function loadItemTable(e, json) {
    $('#type-name-item').text(e.dataPoint.indexLabel);
    $('#amount-item').text(e.dataPoint.y + " LKR");

    table.clear();
    for (var i = 0; i < json.length; i++) {
        var rowData = [];
        rowData.push(json[i].ref_no);
        rowData.push(json[i].txn_date);
        rowData.push(json[i].type_name);
        rowData.push(json[i].qty);
        rowData.push(json[i].selling_price);
        table.row.add(rowData);
    }
    table.draw();

    $('.item-table').show();
    $("html, body").animate({
        scrollTop: $('#item-detail-info').offset().top - 50
    }, 1000);
}


function loadItemTable2(e, json) {
    $('#type-name-item').text(e.dataSeries.name);
    $('#amount-item').text(e.dataPoint.y + " LKR");

    table.clear();
    for (var i = 0; i < json.length; i++) {
        var rowData = [];
        rowData.push(json[i].ref_no);
        rowData.push(json[i].txn_date);
        rowData.push(json[i].type_name);
        rowData.push(json[i].qty);
        rowData.push(json[i].selling_price);
        table.row.add(rowData);
    }
    table.draw();

    $('.item-table').show();
    $("html, body").animate({
        scrollTop: $('#item-detail-info').offset().top - 50
    }, 1000);
}

function convertArrayOfObjectsToCSV(args) {
    var result, ctr, keys, columnDelimiter, lineDelimiter, data;

    data = args.data || null;
    if (data == null || !data.length) {
        return null;
    }

    columnDelimiter = args.columnDelimiter || ',';
    lineDelimiter = args.lineDelimiter || '\n';

    keys = Object.keys(data[0]);

    result = '';
    result += keys.join(columnDelimiter);
    result += lineDelimiter;

    data.forEach(function (item) {
        ctr = 0;
        keys.forEach(function (key) {
            if (ctr > 0)
                result += columnDelimiter;

            result += item[key];
            ctr++;
        });
        result += lineDelimiter;
    });

    return result;
}

function downloadCSV(args) {
    var data, filename, link;
    var csv = convertArrayOfObjectsToCSV({
        data: tableData
    });
    if (csv == null)
        return;

    filename = args.filename || 'export.csv';

    if (!csv.match(/^data:text\/csv/i)) {
        csv = 'data:text/csv;charset=utf-8,' + csv;
    }
    data = encodeURI(csv);

    link = document.createElement('a');
    link.setAttribute('href', data);
    link.setAttribute('download', filename);
    link.click();
}


function printData()
{
    var mywindow = window.open('', '');
    mywindow.document.write('<html><head><title>Product Report - Print</title>');
//    mywindow.document.write('<link rel="stylesheet" href="WEB-INF/RES/CSS/bootstrap.min.css" type="text/css" />');
    mywindow.document.write('</head><body>');
    mywindow.document.write('<table border="1" style="border-collapse: collapse;" width="100%"><thead><tr><th style="padding:7px;">INVOICE NO</th><th style="padding:7px;">DATE</th><th style="padding:7px;">TYPE NAME</th><th style="padding:7px;">QTY</th><th style="padding:7px;">SELLING PRICE</th></tr></thead><tbody>');
    for (var i = 0; i < tableData.length; i++) {
        mywindow.document.write('<tr>');
        mywindow.document.write('<td style="padding:7px;">' + tableData[i].ref_no + '</td>');
        mywindow.document.write('<td style="padding:7px;">' + tableData[i].txn_date + '</td>');
        mywindow.document.write('<td style="padding:7px;">' + tableData[i].type_name + '</td>');
        mywindow.document.write('<td style="padding:7px;">' + tableData[i].qty + '</td>');
        mywindow.document.write('<td style="padding:7px;text-align:right">' + tableData[i].selling_price + '</td>');
        mywindow.document.write('</tr>');
    }
    mywindow.document.write('</tbody>');
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}
