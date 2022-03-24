<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="UTF-8" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Logs page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div>
        <p>System logs:</p>
        <p>${requestScope.logs != null ? requestScope.logs : ""}</p>
    </div>
    <div>
        <c:if test="${requestScope.audit != null}">
            <table class="table">
                <tr>
                    <th>Text message</th>
                    <th>Author</th>
                    <th>Date create</th>
                    <th>User</th>
                </tr>
                <c:forEach items="${requestScope.audit}" var="audit" >
                    <tr>
                        <td><c:out value="${audit.text}" /></td>
                        <td><c:out value="${audit.author.login}" /></td>
                        <td><c:out value="${audit.dtCreate}" /></td>
                        <td><c:out value="${audit.user.login}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
    <form action="<%=request.getContextPath()%>/messenger">
        <div>
            <p>
                <button tabindex="1" type="submit">Назад</button>
            </p>
        </div>
    </form>
</body>
</html>