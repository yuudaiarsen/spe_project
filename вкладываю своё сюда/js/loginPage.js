$('#login_button').click(function () {
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
        },
        error: function (data, status) {
            alert("Ошибка");
        }
    });
});

$('#register_button').click(function () {
    window.document.location = '/registration';
});