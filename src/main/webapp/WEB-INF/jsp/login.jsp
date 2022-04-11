<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Web Notepad</title>
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<div id="container">

    <div id="left">
        <h1>Web Notepad</h1>
        <div id="author">Developed by Roman F</div>
    </div>

    <div id="right">
        <div id="auth-cover">
            <p class="auth-header"> Log in</p>
            <form action="/auth/login" method="POST">
                <input class="auth-input" type="text" name="username" value="" placeholder="Enter login"/>
                <input class="auth-input" type="password" name="password" value="" placeholder="Enter password"/>
                <div><input class="auth-submit" type="submit" value="LOG IN"/></div>
            </form>

            <form name="username" action="/auth/login" method="POST">
                <input type="hidden" name="demoUserName" value="true">
                <input class="log-demo" type="submit" value="LOG IN AS DEMO USER"/><br/>
            </form>
            <a id="reg" href="/auth/register">Registration</a>
        </div>
    </div>

</div>
</body>
</html>