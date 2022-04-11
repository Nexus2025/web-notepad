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
            <p class="auth-header"> Create an account</p>
            <form action="/auth/register" method="POST">
                <input class="auth-input" type="text" name="username" value="" placeholder="Enter login"/>
                <input class="auth-input" type="password" name="password" value="" placeholder="Enter password"/>
                <input class="auth-input" type="password" name="confirmPassword" value="" placeholder="Confirm password"/>
                <div><input class="auth-submit" type="submit" value="CREATE AN ACCOUNT"/></div>
            </form>
            <a id="reg" href="/auth/login">Back to Login</a>
        </div>
    </div>

</div>
</body>
</html>