function showMessage(message, color) {
    $('#warning').empty();
    $('#warning').append(message);
    document.getElementById('warning').style.color = color;
    document.getElementById('warning').style.display = 'block';
}

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
                showMessage(data.message, "black");
                window.location.reload(false);
            },
            error: function (xhr, textStatus, errorThrown) {
                showMessage("Ошибка #" + xhr.status, "red");
            }
        });
    })
});