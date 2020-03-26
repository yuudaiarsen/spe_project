<%@ page import="ru.ikbo1018.models.Type" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <link rel="stylesheet" href="css/request.css">
    <script src="/js/jquery-3.4.1.min.js"></script>
    <meta charset="UTF-8">
</head>
<body>
    <%@include file="/header.jsp"%>
    <section id="main">
		<div class="container">
			<form id="form" enctype="multipart/form-data">
				Выберите нарушение:
        		<select form="form" id="types" name="type" required>
            		<% for(Type type : (List<Type>)request.getAttribute("type_list"))
            		{
                		out.print("<option value=" + type.getId() + ">" + type.getName() + "</option>");
            		}
            		%>
        		</select>
        		<div class="warning" id="warning">Информация о пунктах</div>
        		<div class="error_field" id="error_field"></div>
        		<input type="file" id="files" accept="image/*" title="Загрузите фотографии" class="files" name="files" size="25" multiple>
        		<input type="text" id="address" title="Загрузите фотографии" class="address" name="address" required="" size="33" placeholder="Укажите адрес нарушения">
        		<textarea rows="10" cols="35" name="appeal_text" placeholder="Оставьте ваш комментарий здесь (необязательно)"></textarea>
        		<input type="submit" id="submit_button">
			</form>
		</div>
	</section>
    <%@include file="/footer.jsp"%>
    <script src="/js/appealPage.js"></script>
</body>
</html>
