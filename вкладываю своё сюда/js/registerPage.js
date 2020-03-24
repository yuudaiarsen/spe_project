function showError(error) {
    $('#warning').empty();
    $('#warning').append(error);
    document.getElementById('warning').style.display = 'inline-block';
}

function send() {
    $.ajax({
        url: '/login',
        type: 'post',
        data: {
            'first_name': $('#first_name').val(),
            'last_name': $('#last_name').val(),
            'mid_name': $('#mid_name').val(),
            'phone': $('#phone').val(),
            'email': $('#email').val(),
            'password': $('#password').val()
        },
        success: function (data, status, xhr) {
            if(data.redirect) {
                window.document.location = data.redirect;
            } else {
                showError(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
}
    
document.getElementById('email').onfocus = function() {
    document.getElementById('warning').style.display = 'none';
}