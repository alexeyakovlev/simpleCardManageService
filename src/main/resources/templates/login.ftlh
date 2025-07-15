<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h1>CardManageSystem</h1>
<h4>Вход</h4>
<form action="/login" method="post">
    Электронная почта: <input type="email" name="email"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/><br><br>
</form>
Еще не зарегистрированы? <a href="/registration">Зарегистрироваться</a>
</body>
</html>