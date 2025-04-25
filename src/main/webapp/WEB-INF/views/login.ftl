<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
</head>
<body>
<h2>Вход в аккаунт</h2>

<#if request.getParameter("confirmed")??>
    <p style="color: green;">Ваш аккаунт подтвержден! Теперь вы можете войти.</p>
</#if>

<#if request.getParameter("error")??>
    <p style="color: red;">Ошибка подтверждения. Возможно, ссылка устарела.</p>
</#if>

<form method="post" action="/login">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br><br>

    <button type="submit">Войти</button>
</form>

<p>Нет аккаунта? <a href="/register">Зарегистрироваться</a></p>
</body>
</html>
