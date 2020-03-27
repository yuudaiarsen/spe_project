<%@ page import="java.util.List" %>
<jsp:useBean id="appeal" class="ru.ikbo1018.models.Appeal" scope="request"/>
<html>
<head>
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
    Номер: ${appeal.id}
    Отправлено: ${appeal.sendDate}
    Текст: ${appeal.appealText}
    Тип: ${type}
    Статус: ${status}
    <% for(Integer id : (List<Integer>)request.getAttribute("images"))
    {
        out.print("<img src=/image?id=" + id + " />");
    }%>

    <% if(appeal.getStatus() == 1) {
        out.print("<input type=\"submit\" id=\"deny\">КНОПКА</input>");
    }
    %>

    <script src="/js/viewPage.js"></script>
</body>
</html>
