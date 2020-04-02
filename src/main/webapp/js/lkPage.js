const form = document.getElementById('form');
var elements = form.elements;
var currPage = 0;
form.addEventListener('submit', onSubmit);

data = [];

$('#prev').click(function () {
    currPage--;
    downloadNewPage();
});

$('#next').click(function () {
    currPage++;
    downloadNewPage();
});

function setData() {
    for (var i = 0; i < 6; i++) {
        data.push(elements[i].value);
    }
}

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


function onSubmit(event) {
    for (var i = 0; i < 6; i++) {
        if (elements[i].value != data[i]) {
            if (request(elements[i].id, elements[i].value)) {
                data[i] = elements[i].value;
            }
        }
    }
    setFields();
    event.preventDefault();
}

function request(id, newValue) {
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
                return true;
            } else {
                showError(data.error);
                return false;
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
            return false;
        }
    });
}

// Проверяет, существует ли следующая страница
function checkNewPage (data) {
    if (data[0]) {
        document.getElementById('next').disabled = false;
        return true;
    } else {
        document.getElementById('next').disabled = true;
        return false;
    }
}

function showNewPage (data) {
    for (var i = 0; i < 10; i++) {
        if (data[i]) {
            if (i == 0) {
                $('#list').empty();
            }
            var ref = document.createElement('a');
            ref.setAttribute('href','/view?id=' + data[i].id);
            ref.innerHTML = "Заявление от "+(data[i].send_date);
            $('#list').append(ref);
        } else {
            document.getElementById('next').disabled = true;
            return false;
        }
    }
    downloadNewPage(1);
    return true;
}

function downloadNewPage(onlyCheck = 0) {
    $.ajax({
        url: '/lk',
        type: 'post',
        enctype: 'utf-8',
        data: {
            'page': currPage + onlyCheck,
        },
        success: function (data, status) {
            if (onlyCheck) {
                checkNewPage(data);
            } else {
                if (currPage == 0) {
                    document.getElementById('prev').disabled = true;
                } else {
                    document.getElementById('prev').disabled = false;
                }
                showNewPage(data);
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
}

$(document).ready(function() {
        setData();
        downloadNewPage();
    }
);