function showMessage(message) {
    $('#warning').empty();
    $('#warning').append(message);
}

function showError(message) {
    $('#error_field').empty();
    $('#error_field').append(message);
    document.getElementById('error_field').style.display = 'block';
    $("#error_field").fadeOut(2000);
}

function update()
{
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
                showError(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
}

$('#types').change(function () {
    update();
});

const form = document.getElementById('form');
form.addEventListener('submit', onSubmit);

// submit_button request
function onSubmit(event) {
    var formData = new FormData($("#form")[0]);
    // formData.append('address', 'нет');
    $.ajax({
        url: '/appeal',
        type: 'post',
        contentType: false,
        processData: false,
        data: formData,
        success: function (data, status, xhr) {
            if (data.redirect) {
                window.document.location = data.redirect;
            } else {
                showError(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
    event.preventDefault();
}

$(document).ready(function() {
        update();
    }
);