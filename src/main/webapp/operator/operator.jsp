<jsp:useBean id="appeal" class="ru.ikbo1018.models.Appeal" scope="request"/>
<html>
<head>
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
Номер: <p id="id">${appeal.id}</p><br>
    ${appeal.appealText}
    ${appeal.address}
    ${status}
    ${type}

    <input type="text" id="answer"/>
    <button id="checked">Рассмотрено</button>
    <button id="denied">Отклонено</button>

    <script src="/js/operatorPage.js"></script>
</body>
</html>
