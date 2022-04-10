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
        <div class="user-info">User:</div>
        <div class="logout-div"><a class="logout-link" href="">Logout</a></div>
        <a class="notebook-add-link" href="">Add new notebook +</a>
        <br>
        <br>
        <div class="notebook-size">${notebooks.size()} notebooks:</div>
        <div class="notebooks-scroll">
            <c:forEach var="notebook" items="${notebooks}">
                <div class="notebook-item">
                    <a class="notebook-link" href="/?notebookId=${notebook.id}">${notebook.name}</a>
                </div>
            </c:forEach>
        </div>
        <div id="author">Developed by Roman F</div>
    </div>

    <div id="center">
        <c:if test="${currentNotebook != null}">
            <h2>${currentNotebook.name}</h2>
            <form class="notebook-delete-form" method="post" action="notebook/delete">
                <input type="hidden" name="notebookId" value="${currentNotebook.id}">
                <input class="notebook-delete-link" type="submit" value="Delete notebook">
            </form>
            <p class="rename-p"><a class="notebook-rename-link" href="">Rename notebook</a></p>
            <p><a class="notebook-add-link" href="">Add new note +</a></p>
            <div class="note-size">${notes.size()} notes:</div>
            <div class="notes-scroll">
                <c:forEach var="note" items="${notes}">
                    <div class="note-item">
                        <a class="note-link"
                           href="/?notebookId=${currentNotebook.id}&noteId=${note.id}">${note.name}</a>
                        <p class="note-modified">Last modified: ${note.lastModified.toLocalDate()}</p>
                    </div>
                </c:forEach>
            </div>
        </c:if>
    </div>

    <div id="right">
        <c:if test="${currentNote != null}">
            <div class="note-head">
                <div class="inline"><h2 class="h2-content">${currentNote.name}</h2></div>
                <div class="left-inline">
                    <input class="note-save-link" type="submit" form="content" value="Save note">
                        <%--                <form method="post" action="note/save">--%>
                        <%--                    <input type="hidden" name="noteId value="${currentNote.id}">--%>
                        <%--                    <input class="note-save-link" type="submit" value="Save note">--%>
                        <%--                </form>--%>
                </div>
                <div class="left-inline"><a class="note-rename-link" href="">Rename note</a></div>
                <div class="left-inline">
                    <form method="post" action="note/delete">
                        <input type="hidden" name="noteId value="${currentNote.id}">
                        <input class="note-delete-link" type="submit" value="Delete note">
                    </form>
                </div>
                <div class="left-inline">
                    <p class="note-last-modified">Last modified: ${currentNote.lastModified.toLocalDateTime()}</p>
                </div>
            </div>
            <div class="note-content">
                <form id="content" action="" method="post">
                    <textarea style="width: 100%; height: 80%" name="content">${currentNote.content}</textarea>
                    <input type="hidden" name="noteId" value="${currentNote.id}">
                </form>
            </div>
        </c:if>
    </div>

</div>
</body>
</html>
