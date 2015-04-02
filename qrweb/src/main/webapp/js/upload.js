$(document).ready(function () {
    var uploadObject = $("#fileuploader").uploadFile({
        url: "rest/files/upload/",
        fileName: "file",
        dragdropWidth: 100,
        allowedTypes: "png",
        acceptFiles: "image/",
        showStatusAfterSuccess: false,
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