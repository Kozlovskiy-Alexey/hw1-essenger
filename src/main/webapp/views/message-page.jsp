<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Message page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="index_style">
        <p style="color: green">${requestScope.sendConfirmation != null ? requestScope.sendConfirmation : ""}</p>
        Отправьте сообщение.
        <form action="<%=request.getContextPath()%>/messenger/message" method="post">
            <p>Кому:</p>
            <input tabindex="1" type="text" name="login" placeholder="Введите логин">
            <p>Сообщение:</p>
            <textarea tabindex="2" name="message" placeholder="Введите сообщение"></textarea>
            <p><button tabindex="3" type="submit">Отправить</button></p>
            <p><button tabindex="4" type="reset">Очистить форму</button></p>
        </form>
        <form action="<%=request.getContextPath()%>/messenger/userPage">
            <p><button tabindex="5" type="submit">Назад</button></p>
        </form>
    </div>
</body>
</html>