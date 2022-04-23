<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Web Notepad</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<div id="container">

    <div id="left">
        <h1>Web Notepad</h1>
        <security:authentication var="user" property="principal" />
        <div class="user-info">User: ${user.username}</div>
        <form:form action="${pageContext.request.contextPath}/logout" method="POST">
            <input class="logout-button" type="submit" value="Logout" />
            <a class="admin-button" href="/">Go to main</a>
        </form:form>
        <div id="author">Developed by Roman F</div>
    </div>

    <div id="right">
        <div>
            <form class="create-user" method="post" action="/admin/user/create">
                <div class="create-header">Add new user:</div>
                <p><input class="user-input" type="text" name="username" value="" placeholder="Enter username"></p>
                <p><input class="user-input" type="password" name="password" value="" placeholder="Enter password"></p>
                <p><select name="role">
                    <option value="ROLE_USER">ROLE_USER</option>
                    <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                </select></p>
                <input class="create-form-submit" type="submit" value="SAVE">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="user-table-scroll">
            <table id="user-table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>roles</th>
                    <th>reg. date</th>
                    <th>actions</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>
                            <c:forEach items="${user.roles}" var="role">
                                ${role.name}
                            </c:forEach>
                        </td>
                        <fmt:parseDate value="${user.registrationDate.toLocalDateTime()}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="parsedDateTime" />
                        <td><fmt:formatDate value="${parsedDateTime}" pattern="d MMM yyyy HH:mm" /></td>
                        <td>
                            <form method="post" action="/admin/user/delete">
                                <input type="hidden" name="userId" value="${user.id}">
                                <input class="delete-user-btn" type="submit" value="DELETE">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</div>
</body>
</html>