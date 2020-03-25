<%@ page import="ru.ikbo1018.models.Type" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>

    <div id="description"></div>

    <form method="post" action="/appeal" enctype="multipart/form-data">
        <input type="text" name="appeal_text">
        <select id="types" name="type">
            <% for(Type type : (List<Type>)request.getAttribute("type_list"))
            {
                out.print("<option value=" + type.getId() + ">" + type.getName() + "</option>");
            }
            %>
        </select>
        <input type="file" name="files" multiple>
        <input type="submit">
    </form>
    <script src="/js/appealPage.js"></script>
</body>
</html>
