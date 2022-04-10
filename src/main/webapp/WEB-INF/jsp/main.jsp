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
        <br>
        <c:forEach var="notebook" items="${notebooks}">
            <a style="color: white; padding-left: 15px" href="/?notebookId=${notebook.id}">${notebook.name}<br></a>
        </c:forEach>
    </div>
    <div id="center">
        <h2>${currentNotebook.name}</h2>
        <c:forEach var="note" items="${notes}">
            <a style="color: black; padding-left: 15px" href="/?notebookId=${currentNotebook.id}&noteId=${note.id}">${note.name}<br></a>
        </c:forEach>
    </div>
    <div id="right">
        <h2>Note's content</h2>
        <div>${currentNote.content}</div>
    </div>
</div>
</body>
</html>
