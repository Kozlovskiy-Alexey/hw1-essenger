<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="index_style">
        <p>Здравствуйте, ${requestScope.person}!</p>
        <p>Выберите действие</p>
        <form action="<%=request.getContextPath()%>/messenger/message" method="get">
            <button tabindex="1" type="submit">Хочу отправить сообщение</button>
        </form>
        <form action="<%=request.getContextPath()%>/messenger/chats" method="get">
            <button tabindex="2" type="submit">Входящие сообщения</button>
        </form>
        <form action="<%=request.getContextPath()%>/messenger/signOut" method="get">
            <button tabindex="3" type="submit">Выйти</button>
        </form>
    </div>
</body>
</html>