function showMessage(message) {
    $('#warning').empty();
    $('#warning').append(message);
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
                showMessage(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showMessage("Ошибка #" + xhr.status);
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
    /*$.each($('#files').files, function (i, file) {
        formData.append(i, file);
    });*/
    formData.append('address', 'temp');
    $.ajax({
        url: '/appeal',
        type: 'post',
        enctype: 'utf-8',
        contentType: false,
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

$(document).ready(function() {
        update();
    }
);