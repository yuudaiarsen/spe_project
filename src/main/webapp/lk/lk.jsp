<jsp:useBean id="account" class="ru.ikbo1018.models.Account" scope="request"/>
<html>
<head>
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
    Имя: <span id="first_name">${account.firstName}</span> <button value="first_name">Изменить</button><br>
    Фамилия: <span id="last_name">${account.lastName}</span>  <button value="last_name">Изменить</button><br>
    Отчество: <span id="mid_name">${account.midName}</span>  <button value="mid_name">Изменить</button><br>
    Номер телефона: <span id="phone">${account.phone}</span>  <button value="phone">Изменить</button><br>
    Email: ${account.email}<br>
    Дата регистрации: ${account.regDate}
    <br>
    <button id="password_button">Изменить пароль</button>

    <script src="/js/lkPage.js"></script>
</body>
</html>
