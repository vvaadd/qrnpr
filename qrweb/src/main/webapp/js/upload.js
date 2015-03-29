$(document).ready(function () {
    var uploadObject = $("#fileuploader").uploadFile({
        url: "rest/files/upload/",
        fileName: "file",
        multiple: false,
        onSuccess: function (files, data, xhr, pd) {
            var filePath = data;
            console.log(filePath);
            // TODO выбрать функцию
            $('#uploadedImage').append(
                '<img src="' + filePath + '">'
            );
        }
    });

});