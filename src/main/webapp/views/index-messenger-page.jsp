<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Messenger</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="index_style">
        <p>Выберите действие:</p>
        <form action="<%=request.getContextPath()%>/messenger/signUp">
           <p>
                <button tabindex="1" type="submit">Зарегистрироваться</button>
           </p>
        </form>
        <form action="<%=request.getContextPath()%>/messenger/signIn">
            <p>
                 <button tabindex="2" type="submit">Войти</button>
            </p>
        </form>
        <form action="<%=request.getContextPath()%>/messenger/logs">
            <p>
                <button tabindex="3" type="submit">System logs</button>
            </p>
        </form>
    </div>
</body>
</html>
