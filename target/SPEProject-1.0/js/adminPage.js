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

$('#violSelect').change(function () {
    updateTypeList(true);
});

$('#add_button').click(function () {
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'create_type',
            'name': $('#violName_field').val(),
            'desc': $('#violDesc_field').val()
        },
        success: function (data, status) {
            updateTypeList();
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
});

$('#delete_button').click(function () {
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'delete_type',
            'id': $('#violId_field').val()
        },
        success: function (data, status) {
            updateTypeList();
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
});

function updateTypeList(getDesc = false)
{
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'get_type_list'
        },
        success: function (data, status) {
            // Получение описания выбраного нарушения
            if (getDesc) {
                var i = document.getElementById("violSelect").selectedIndex;
                showMessage(data[i].desc);
                return;
            }
            $('#violSelect').empty();
            var i = 0;
            while (true) {
                if (data[i]) {
                    var ref = document.createElement('option');
                    ref.innerHTML = i + data[i].name;
                    $('#violSelect').append(ref);
                    i++;
                } else {
                    break;
                }
            }
        },
        error: function (xhr, textStatus, errorThrown)  {
            showError("Ошибка #" + xhr.status);
        }
    });
}

function setFields (id, fName, lName, mName, phone, sLvl, email, rDate) {
    $('#id_field').val(id);
    $('#firstName_field').val(fName);
    $('#lastName_field').val(lName);
    $('#midName_field').val(mName);
    $('#phone_field').val(phone);
    $('#secLevel_field').val(sLvl);
    $('#email_field').val(email);
    $('#regDate_field').val(rDate);
}

$('#findAccount_button').click(function () {
    var id = $('#id_field').val();
    if(id)
    {
        $.ajax({
            url: '/admin',
            type: 'post',
            data: {
                'action': 'find',
                'id': id
            },
            success: function (data, status) {
                setFields(data.id, data.first_name, data.last_name, data.mid_name, data.phone,
                    data.sec_level, data.email, data.reg_date);
                // id, first_name, last_name, mid_name, email, phone, reg_date, sec_level (уровень доступа)
            },
            error: function (xhr, textStatus, errorThrown)  {
                showError("Ошибка #" + xhr.status);
            }
        });
    }
});

$('#save_button').click(function () {
    // Код сохранения изменений
});

$(document).ready(function() {
        updateTypeList();
    }
);