function showError(message) {
    $('#warning').empty();
    $('#warning').append(message);
    document.getElementById('warning').style.display = 'inline-block';
    $("#warning").fadeOut(2000);
}

$('#checked').click(function () {
    if($('#answer').val())
    {
        $.ajax({
            url: '/operator',
            type: 'post',
            data: {
                'action': 'checked',
                'answer': $('#answer').val(),
                'id': $('#id').text().slice(7)
            },
            success: function (data, status) {
                window.location.reload(true);
            },
            error: function (xhr, textStatus, errorThrown)  {
                showError("Ошибка #" + xhr.status);
            }
        });
    }
});

$('#denied').click(function () {
    if($('#answer').val())
    {
        $.ajax({
            url: '/operator',
            type: 'post',
            data: {
                'action': 'denied',
                'answer': $('#answer').val(),
                'id': $('#id').text().slice(7)
            },
            success: function (data, status) {
                window.location.reload(true);
            },
            error: function (xhr, textStatus, errorThrown)  {
                showError("Ошибка #" + xhr.status);
            }
        });
    }
});
