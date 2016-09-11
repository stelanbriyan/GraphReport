/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(function () {
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

    $('.selectpicker').selectpicker({
        style: 'btn-info',
        size: 12
    });

    $('#search').on('click', function () {
        var repList = $('#list').val();
        var monthList = $('#monthList').val();
        var jsonData = {
            "months": monthList,
            "reps": repList
        };

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