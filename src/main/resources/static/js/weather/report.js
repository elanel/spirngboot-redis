/**
 * report 页面下拉框事件
 * **/
$(function(){
    $("#selectCityId").change(function () {
        var cityId = $("#selectCityId").val();
        var url = '/report/cityId/'+cityId;
        //alert(url);
        window.location.href = url;
    })
});

