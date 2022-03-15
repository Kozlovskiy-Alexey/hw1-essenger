<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chats page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div>
        <p>Ваши сообщения:</p>
        <p>${requestScope.messageIsEmpty != null ? requestScope.messageIsEmpty : ""}</p>
    </div>
    <div>
        <c:if test="${requestScope.messages != null}">
            <table class="table">
                <tr>
                    <th>Дата и время отправки</th>
                    <th>От кого</th>
                    <th>Текст сообщения</th>
                </tr>
                <c:forEach items="${requestScope.messages}" var="sms" >
                    <tr>
                        <td><c:out value="${sms.dateTime}" /></td>
                        <td><c:out value="${sms.fromLogin}" /></td>
                        <td><c:out value="${sms.text}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <form action="<%=request.getContextPath()%>/messenger/message">
        <div>
            <p>
                <button tabindex="1" type="submit">Назад</button>
            </p>
        </div>
    </form>
</body>
</html>