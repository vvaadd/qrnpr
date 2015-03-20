$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$(function () {
    $('#qrform').submit(function () {
        var data = $('#result').text(JSON.stringify($('form').serializeObject()));
        var posting = $.ajax({
            type: "POST",
            url: "rest/qr/createqr/",
            data: JSON.stringify($('form').serializeObject()),
            dataType: "json",
            contentType: 'application/json'
            
        });
            
    // Put the results in a div
    posting.done(function (data) {
        var content = $(data).find("#content");
        $("#result").empty().append(content);
    });
    return false;
});
})
;