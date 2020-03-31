$('#checked').click(function () {
    if(getInputText())
    {
        $.ajax({
            url: '/operator',
            type: 'post',
            data: {
                'action': 'checked',
                'answer': getInputText(),
                'id': $('#id').text()
            },
            success: function (data, status) {
                window.location.reload(true);
            },
            error: function (xhr, textStatus, errorThrown)  {
                // показать ошибку
            }
        });
    }
});

$('#denied').click(function () {
    if(getInputText())
    {
        $.ajax({
            url: '/operator',
            type: 'post',
            data: {
                'action': 'denied',
                'answer': getInputText(),
                'id': $('#id').text()
            },
            success: function (data, status) {
                window.location.reload(true);
            },
            error: function (xhr, textStatus, errorThrown)  {
                // показать ошибку
            }
        });
    }
});

function getInputText() {
    return $('#answer').val();
}