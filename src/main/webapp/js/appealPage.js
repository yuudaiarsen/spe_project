$('#types').change(function () {
    $.ajax({
        url: '/appeal',
        type: 'post',
        data: {
            'value': $('#types').val()
        },
        success: function (data, status) {
            $('#description').empty();
            $('#description').append(data.desc);
        }
    });
});