<jsp:useBean id="account" class="ru.ikbo1018.models.Account" scope="request"/>
<html>
<head>
    <link rel="stylesheet" href="/css/lk.css"/>
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
    <%@include file="/header.jsp"%>
    <section id="main">
        <div class="container">
            <div class="information">
                <form id="form">
                    <div class="firstName">
                        <label for="firstName_field">Имя:</label><input required type="text" pattern="[A-Za-zА-Яа-я]+" id="firstName_field">
                    </div>
                    <div class="lastName">
                        <label for="lastName_field">Фамилия:</label><input required type="text" pattern="[A-Za-zА-Яа-я]+" id="lastName_field">
                    </div>
                    <div class="midName">
                        <label for="midName_field">Отчество:</label><input required type="text" pattern="[A-Za-zА-Яа-я]+" id="midName_field">
                    </div>
                    <div class="phone">
                        <label for="phone_field">Номер телефона:</label><input required type="tel" pattern="[0-9]{8,12}" id="phone_field">
                    </div>
                    <div class="email">
                        <label for="email_field">Email:</label><input type="text" disabled="disabled" id="email_field">
                    </div>
                    <div class="regDate">
                        <label for="regDate_field">Дата регистрации:</label><input disabled="disabled" type="text" id="regDate_field">
                    </div>
                    <div class="buttons">
                        <input type="submit" id="submit_button" value="Сохранить">
                        <input type="reset" id="reset_button" value="Отменить">
                    </div>
                </form>
                <div id="warning"></div>
                <div id="error_field"></div>
            </div>
            <div class="references" id="references">
                <a href="#">Подать заявление</a>
                <!-- ссылки на страницы заявлений -->
            </div>
        </div>
    </section>
    <%@include file="/footer.jsp"%>
    <script src="/js/lkPage.js"></script>
</body>
</html>
