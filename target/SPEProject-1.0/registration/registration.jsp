<!DOCTYPE html>
<html lang="ru">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <link rel="stylesheet" href="css/registration.css">
    <script src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
<%@ include file="/header.jsp"%>
<section id="main">
    <div class="container">
        <div class="main">
            <div class="title">
                <h1>Создать аккаунт</h1>
                <h2>Быстро и легко.</h2>
            </div>
            <form onsubmit="send();return false">
                <div class="fields">
                    <input type="text" name="first_name" id="first_name" placeholder="Имя" pattern="[a-zA-ZА-Яа-я]+" size="25" required><br>
                    <input type="text" name="last_name" id="last_name" placeholder="Фамилия" pattern="[А-Яа-яa-zA-Z]+" size="25" required><br>
                    <input type="text" name="mid_name" id="mid_name" placeholder="Отчество" pattern="[А-Яа-яa-zA-Z]+" size="25"><br>
                    <input type="text" name="phone" id="phone" placeholder="Телефон" title="От 10 до 12 цифр без разделителей" pattern="^[\+]?[1-9][0-9]{9,11}$" size="25"><br>
                    <input type="email" name="email" id="email" placeholder="Почтовый адрес" size="25" required><div id="warning"></div><br>
                    <input type="password" id="password" placeholder="Пароль" title="Минимум 8 символов"  pattern=".{8,}" size="25" required>
                </div>
                <div class="buttons clearfix">
                    <input class="button" value="Зарегистрироваться" type="submit" name="submit" id="submit_button">
                </div>
            </form>
            <div class="log_part">
                <p>Есть аккаунт?</p>
                <a href="index.html">Войти</a>
            </div>
        </div>
    </div>
</section>
<%@ include file="/footer.jsp"%>
<script src="js/registerPage.js"></script>
</body>