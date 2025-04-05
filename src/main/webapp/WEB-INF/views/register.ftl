<!DOCTYPE html>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Регистрация</h2>
<form method="post" action="/register">
    <label>Имя пользователя: <input type="text" name="username"/></label><br/>
    <label>Пароль: <input type="password" name="password"/></label><br/>
    <button type="submit">Зарегистрироваться</button>
</form>
<a href="/login">Уже есть аккаунт?</a>
</body>
</html>