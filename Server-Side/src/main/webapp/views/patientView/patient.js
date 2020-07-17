$("#logout").css("display","block");

function postConfirm() {

    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    var fileText = $("#fileText").val();

    var data = new FormData();
    var customFile = $('#customFile').prop('files')[0];
    data.append('file', customFile);
    data.append("fileText", fileText);

    var headers={};
    headers[csrfHeader]=csrfToken;
    $.ajax({
        type: "POST",
        url: "/fileUpload",
        data: data,
        cache: false,
        contentType: false,
        processData: false,
        headers:headers
    })
        .done(function (result) {

            if (result=="success"){
                window.location.href = "/Patient/index";
            }
        })
        .fail(function (jqXHR, textStatus) {
            alert(jqXHR.responseText);
        });
}
    
    
