$(document).ready(function () {
    var uploadObject = $("#fileuploader").uploadFile({
        url: "rest/files/upload/",
        fileName: "file",
        multiple: false,
        onSuccess: function (files, data, xhr, pd) {
            //var obj = jQuery.parseJSON(data);
            // TODO выбрать функцию
            $('#uploadedImage').append(
                '<img src="' + data.url + '" height="' + data.height + '" width="' + data.width + '">'
            );
        }
    });

});