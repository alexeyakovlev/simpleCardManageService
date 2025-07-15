<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>CardManageService</h1>
<h4>Информация о пользователе </h4>
 <b>Имя: </b>${user.name}<br>
 <b>Электронная почта: </b>${user.email}<br>
 <b>Дата рождения: </b>${user.dateOfBirth}<br>
<h4>Карты пользователя ${user.name}</h4>
<#list cards as card>
    <div>
        <p><b>${card.number}</b> ${card.status} руб.| <a href="/user/${card.id}"> Подробнее...</a></p>
    </div>
<#else>
    <h3>Товаров нет</h3>
</#list>
</body>
</html>