function showError(error) {
    $('#warning').empty();
    $('#warning').append(error);
    document.getElementById('warning').style.display = 'inline-block';
}

$('#login_button').click(function () {
    document.getElementById('warning').style.display = 'none';
    $.ajax({
        url: '/login',
        type: 'post',
        data: {
            'email': $('#email').val(),
            'password': $('#password').val()
        },
        success: function (data, status) {
            if(data.redirect) {
                window.document.location = data.redirect;
            }
            else
            {
                showError(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
});

$('#register_button').click(function () {
    window.document.location = '/registration';
});