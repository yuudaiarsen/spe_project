$('#find_account').click(function () {
    var id = $('#account_number').val();
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
                // вывод информации об аккаунте через объект data, поля:
                // id, first_name, last_name, mid_name, email, phone, reg_date, sec_level (уровень доступа)
            },
            error: function (xhr, textStatus, errorThrown)  {
                // вывод ошибки
            }
        });
    }
});

function updateTypeList()
{
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'get_type_list'
        },
        success: function (data, status) {
            // вывод списка типов нарушения из объекта data[i] (i - id типа), поля:
            // name, desc (описание)
        },
        error: function (xhr, textStatus, errorThrown)  {
            // вывод ошибки
        }
    });
}

$('#type_list').click(function () {
    updateTypeList();
});

$('#type_create').click(function() {
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'create_type',
            'name': $('#type_name').val(),
            'desc': $('#type_desc').val()
        },
        success: function (data, status) {
            updateTypeList();
        },
        error: function (xhr, textStatus, errorThrown)  {
            // вывод ошибки
        }
    });
});

$('#type_delete').click(function(){
    $.ajax({
        url: '/admin',
        type: 'post',
        data: {
            'action': 'delete_type',
            'id': $('#type_number').val()
        },
        success: function (data, status) {
            updateTypeList();
        },
        error: function (xhr, textStatus, errorThrown)  {
            // вывод ошибки
        }
    });
});