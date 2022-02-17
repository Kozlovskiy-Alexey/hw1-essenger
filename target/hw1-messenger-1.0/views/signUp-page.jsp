<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="index_style">
        <p style="color: red">
            ${requestScope.requiredParameter != null ? requestScope.requiredParameter : ""}
        </p>
        <p>Пройдите регистрацию.</p>
        <form action="<%=request.getContextPath()%>/messenger/signUp" method="post">
            <table>
                <tr>
                    <td>Введите логин</td>
                    <td><input tabindex="1" type="text" name="login" value="" placeholder="Введите логин"></td>
                </tr>
                <tr>
                    <td>Введите пароль</td>
                    <td><input tabindex="2" type="password" name="password" value="" placeholder="Введите пароль"></td>
                </tr>
                <tr>
                    <td>Введите имя</td>
                    <td><input tabindex="3" type="text" name="firstName" value="" placeholder="Имя"></td>
                </tr>
                <tr>
                    <td>Введите фамилию</td>
                    <td><input tabindex="4" type="text" name="lastName" value="" placeholder="Фамилия"></td>
                </tr>
                <tr>
                    <td>Введите дату рождения</td>
                    <td><input tabindex="6" type="date" name="birthday" value="" placeholder="Дата рождения"></td>
                </tr>
            </table>
            <p><button tabindex="7" type="submit">Зарегистрироваться</button></p>
            <p><button tabindex="8" type="reset">Очистить форму</button></p>
        </form>
        <form action="<%=request.getContextPath()%>/messenger">
            <p><button tabindex="9" type="submit">Назад</button></p>
        </form>
    </div>
</body>
</html>