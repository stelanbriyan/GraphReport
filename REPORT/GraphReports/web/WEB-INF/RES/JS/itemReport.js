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
        var data = {
            "months": monthList,
            "items": itemList
        };
        
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "v1/web/itemreport/2016/get",
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (data, textStatus, jqXHR) {
                alert(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(errorThrown);
            }
        });
    });
});