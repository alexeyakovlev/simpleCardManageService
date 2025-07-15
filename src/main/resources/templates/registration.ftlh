<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title> Registration </title>
</head>
<body>
<h1>CardManageService</h1>
<h4>Регистрация нового пользователя</h4>
<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br><br>
    Электронная почта: <input type="text" name="email"><br><br>
    Дата рождения: <input type="date" name="dateOfBirth"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<div style="margin-top: 20px;">
    Уже есть аккаунт? <a href="/login">Войти</a>
</div>
</body>
</html>