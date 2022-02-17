<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="index_style">
        <p style="color: red">${requestScope.wrongValidation != null ? requestScope.wrongValidation : ""}</p>
        <p>Войдите в систему.</p>
        <form action="<%=request.getContextPath()%>/messenger/signIn" method="post">
            <p>Введите логин:</p>
            <input tabindex="1" type="text" name="login" placeholder="Введите логин">
            <p>Введите пароль:</p>
            <input tabindex="2" type="password" name="password" placeholder="Введите пароль">
            <p><button tabindex="3" type="submit">Войти</button></p>
            <p><button tabindex="4" type="reset">Очистить форму</button></p>
        </form>
        <form action="<%=request.getContextPath()%>/messenger">
            <p><button tabindex="5" type="submit">Назад</button></p>
        </form>
    </div>
</body>
</html>