<%@ page import="java.util.List" %>
<jsp:useBean id="appeal" class="ru.ikbo1018.models.Appeal" scope="request"/>
<html>
<head>
    <link rel="stylesheet" href="/css/view.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
    <%@include file="/header.jsp"%>
    <section id="main">
        <div class="container">
            <div class="info">
                <div class="info_text">
                    <h1>Информация об обращении</h1>
                    <p>Номер: ${appeal.id}</p>
                    <p>Дата отправления: ${appeal.sendDate}</p>
                    <p>Текст обращения: ${appeal.appealText}</p>
                    <p>Тип нарушения: ${type}</p>
                    <p>Статус: ${status}</p>
                    <p>Адрес: ${appeal.address}</p>
                    <h1>Материалы</h1>
                </div>
                <div class="info_image">
                    <% for(Integer id : (List<Integer>)request.getAttribute("images"))
                    {
                        out.print("<img src=/image?id=" + id + " />");
                    }%>
                </div>
                <div class="reject_button">
                    <% if(appeal.getStatus() == 1){
                        out.print("<button id=\"deny\">Отозвать обращение</button>");
                    }
                    %>
                </div>
                <div id="warning"></div>
            </div>
        </div>
    </section>
    <%@include file="/footer.jsp"%>
    <script src="/js/viewPage.js"></script>
</body>
</html>
