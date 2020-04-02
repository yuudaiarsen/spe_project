<html>
<head>
    <link rel="stylesheet" href="css/admin.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<section id="main">
    <div class="container">
        <div class="information">
            <h3>Поиск/измение данных пользователя по ID</h3>
            <form id="form">
                <div class="id">
                    <label for="id_field">ID:</label><input type="text" pattern="[0-9]+" id="id_field">
                </div>
                <div class="firstName">
                    <label for="firstName_field">Имя:</label><input type="text" pattern="[A-Za-zА-Яа-я]+" id="firstName_field">
                </div>
                <div class="lastName">
                    <label for="lastName_field">Фамилия:</label><input type="text" pattern="[A-Za-zА-Яа-я]+" id="lastName_field">
                </div>
                <div class="midName">
                    <label for="midName_field">Отчество:</label><input type="text" pattern="[A-Za-zА-Яа-я]+" id="midName_field">
                </div>
                <div class="phone">
                    <label for="phone_field">Номер телефона:</label><input type="tel" pattern="[0-9]{8,12}" id="phone_field">
                </div>
                <div class="secLevel">
                    <label for="secLevel_field">Уровень доступа:</label><input type="text" pattern="[A-Za-zА-Яа-я]+" id="secLevel_field">
                </div>
                <div class="email">
                    <label for="email_field">Email:</label><input type="text" disabled="disabled" id="email_field">
                </div>
                <div class="regDate">
                    <label for="regDate_field">Дата регистрации:</label><input disabled="disabled" type="text" id="regDate_field">
                </div>
                <div class="buttons">
                    <button class="findAccount_button" id="findAccount_button">Поиск</button>
                    <button class="save_button" id="save_button">Сохранить</button>
                </div>
            </form>
            <div id="error_field"></div>
        </div>
        <div class="violEdit" id="violEdit">
            <h3>Редактировать список нарушений</h3>
            <div class="editFields">
                <select id="violSelect">
                    <option>Название нарушения</option>
                </select>
                <div class="violId">
                    <input required type="text" pattern="[0-9]+" id="violId_field" placeholder="ID нарушения ">
                </div>
                <div class="violName">
                    <input required type="text" pattern="[A-Za-zА-Яа-я]+" id="violName_field" placeholder="Название нарушения">
                </div>
                <div class="violDesc">
                    <textarea id="violDesc_field" placeholder="Описание нарушения (для добавления)" rows="5"></textarea>
                </div>
                <div class="buttons">
                    <button class="add_button" id="add_button">Добавить</button>
                    <button class="delete_button" id="delete_button">Удалить</button>
                </div>
                <div id="warning">Удаление происходит по полю ID, добавление - по остальным двум</div>
            </div>
        </div>
    </div>
</section>

    <script src="/js/adminPage.js"></script>
</body>
</html>
