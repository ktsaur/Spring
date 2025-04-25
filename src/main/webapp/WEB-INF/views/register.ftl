<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>
<form method="post" action="/register">
    <div>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" value="${user.name!}" required>
    </div>

    <div>
        <label for="username">Email (логин):</label>
        <input type="email" id="username" name="username" value="${user.username!}" required>
    </div>

    <div>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
    </div>

    <div>
        <button type="submit">Зарегистрироваться</button>
    </div>
</form>
</body>
</html>
