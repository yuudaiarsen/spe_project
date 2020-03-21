<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<title>Название</title>
	<link rel="stylesheet" type="text/css" href="/css/main.css">
	<script src="/js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<section id="main">
		<div class="container">
			<div class="main clearfix">
				<div class="login_form">
					<div class="fields">
						<input type="text" id="email" placeholder="Почтовый адрес" size="35"><br>
						<input type="password" id="password" placeholder="Пароль" size="35">
					</div>
					<div class="buttons">
						<input id="login_button" class="button" value="Вход" type="submit" name="submit">
						<p>или</p>
						<button id="register_button" class="button">Регистрация</button>
					</div>
				</div>
				<div class="about">
					<h3>О нас</h3>
					<p>Система обработки обращений автоматизирует процесс подачи и дальнейшей обработки обращений о нарушениях правил парковки в городе. Система позволяет своевременно сообщать о нарушениях правил парковки в ведомства, ответственные за организацию дорожного движения города. <br> Целью создания системы является увеличение процента выявленных нарушений правил парковки в городе. Критерием достижения цели является увеличение среднего показателя выявления нарушений правил парковки.</p>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
	<script src="/js/loginPage.js"></script>
</body>