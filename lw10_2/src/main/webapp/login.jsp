<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
</head>
<body>
	<div>
		Введите имя пользователя и пароль
	</div>
	<form action="login_servlet" method="post">
		<div>Логин</div>
		<div>
			<input name="login" />
		</div>
		<div>Пароль</div>
		<div>
			<input name="password" />
		</div>
		<div>
			<input type="submit" />
		</div>
	</form>
	<a href="register.html">Регистрация</a>

</body>
</html>