$('button').click(function () {
    if($(this).val()) {
        var id = '#' +$(this).val();
        var oldText = $(id).text();
        $(this).hide();
        $(id).replaceWith(
            '<input id="' + $(this).val() + '" type="text" value="' + oldText +
        '" />');
        $(id).focus();
        $(id).select();
        var button = $(this);
        $(id).focusout(function () {
            var newText = $(this).val();
            var elemId = $(this).attr('id');
            $(this).replaceWith('<span id="' + elemId + '">' + newText + '</span>');
            button.show();
            $.ajax({
                url: '/lk',
                type: 'post',
                enctype: 'utf-8',
                data: {
                    'field': elemId,
                    'data': newText
                },
                success: function (data, status) {
                    // вывод сообщения об успехе (data.message)
                    // либо вывод сообщения об ошибке (data.error)
                    if(data.error)
                    {
                        $('#' + elemId).text(oldText);
                    }
                },
                error: function (xhr, textStatus, errorThrown)  {
                    // показать ошибку
                }
            });
        })
    }
});