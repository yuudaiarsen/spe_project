<jsp:useBean id="appeal" class="ru.ikbo1018.models.Appeal" scope="request"/>
<html>
<head>
</head>
<body>
    Номер: ${appeal.id}
    Отправлено: ${appeal.sendDate}
    Текст: ${appeal.appealText}
</body>
</html>
