function showMessage(message) {
    $('#warning').empty();
    $('#warning').append(message);
}

$('#types').change(function () {
    $.ajax({
        url: '/appeal',
        type: 'post',
        data: {
            'value': $('#types').val()
        },
        success: function (data, status) {
            if (data.desc) {
                showMessage(data.desc);
            } else {
                showMessage(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showMessage("Ошибка #" + xhr.status);
        }
    });
});

const form = document.getElementById('form');
form.addEventListener('submit', onSubmit);

// submit_button request
function onSubmit(event) {
    var formData = new FormData($("#form")[0]);
    $.ajax({
        url: '/appeal',
        type: 'post',
        processData: false,
        data: formData,
        success: function (data, status, xhr) {
            if (data.redirect) {
                window.document.location = data.redirect;
            } else {
                showMessage(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showMessage("Ошибка #" + xhr.status);
        }
    });
    event.preventDefault();
}

