<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<html>
<head>
    <title>Web Notepad</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <sec:authorize access="isAuthenticated()">
        <% response.sendRedirect("/"); %>
    </sec:authorize>
</head>
<body>
<div id="container">

    <div id="left">
        <h1>Web Notepad</h1>
        <div id="author">Developed by Roman F</div>
    </div>

    <div id="right">
        <div id="auth-cover">

            <form:form method="POST" modelAttribute="userForm">
                <p class="auth-header"> Log in</p>
                <spring:bind path="username">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="text" path="username" class="auth-input" placeholder="Username"
                                    autofocus="true"></form:input>
                        <form:errors cssClass="user-from-err" path="username"></form:errors>
                    </div>
                </spring:bind>
                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <form:input type="password" path="password" class="auth-input" placeholder="Password"></form:input>
                        <form:errors cssClass="user-from-err" path="password"></form:errors>
                    </div>
                </spring:bind>
                <div><input type="checkbox" name="remember-me"><a class="remember-checkbox" >Remember me</a></div>
                <div><input class="auth-submit" type="submit" value="LOG IN"/></div>
            </form:form>

            <p><a class="log-demo" href="/login/demo">LOG IN AS DEMO USER</a></p><br>
            <p><a id="reg" href="/register">Registration</a></p>
        </div>
    </div>

</div>
</body>
</html>