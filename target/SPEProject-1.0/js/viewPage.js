    $(document).ready(function () {
        $('#deny').click(function () {
            $.ajax({
                url: '/view',
                type: 'post',
                data: {
                    'action': 'deny',
                    'id': new URLSearchParams(window.location.search).get('id')
                },
                success: function (data, status) {
                    // вывести ответное сообщение message
                },
                error: function (xhr, textStatus, errorThrown) {
                    // показать ошибку
                }
            });
        })
    });