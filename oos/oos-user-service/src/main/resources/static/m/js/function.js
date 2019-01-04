var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var csrfName = $("meta[name='_csrf_name']").attr("content");
var mid = $("#userMid").val();

$(document).ajaxSend(function(e,xhr,options){
    xhr.setRequestHeader(header, token);
 });
