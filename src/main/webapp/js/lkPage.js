const form = document.getElementById('form');
var elements = form.elements;
form.addEventListener('reset', onReset);
form.addEventListener('submit', onSubmit);

//Сохранение информации о пользователе в массиве
data = [${account.firstName},${account.lastName},${account.midName},
    ${account.phone},${account.email},${account.regDate}];

// data = ["и","ф","о","12345678","почта","дата"];

function setFields () {
    $('#firstName_field').val(data[0]);
    $('#lastName_field').val(data[1]);
    $('#midName_field').val(data[2]);
    $('#phone_field').val(data[3]);
    $('#email_field').val(data[4]);
    $('#regDate_field').val(data[5]);
}

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

function onReset(event) {
    setFields();
    event.preventDefault();
}

function onSubmit(event) {
    for (var i = 0; i < 6; i++) {
        if (elements[i].value != data[i]) {
            console.log("inside");
            request(elements[i].id, elements[i].value, i);
        }
    }
    setFields();
    event.preventDefault();
}

function request(id, newValue, i) {

    $.ajax({
        url: '/lk',
        type: 'post',
        enctype: 'utf-8',
        data: {
            'field': id,
            'data': newValue
        },
        success: function (data, status) {
            if (data.desc) {
                showMessage(data.message);
            } else {
                showError(data.error);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
            // $('#' + id).val(data[i]);
        }
    });
}

function downloadNewPage() {
    $.ajax({
        url: '/lk',
        type: 'post',
        enctype: 'utf-8',
        data: {
            'page': 0, // ?
        },
        success: function (data, status) {
            for (var i = 0; i < 10; i++) {
                if (data[i]) {
                    var ref = document.createElement('a');
                    ref.setAttribute('href','/view?id=' + data[i].id);
                    ref.innerHTML = "Заявление"+(i+1);
                    $('#references').append(ref);
                } else {
                    return false;
                }
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
}

$(document).ready(function() {
        setFields();
        downloadNewPage();
    }
);