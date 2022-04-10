<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Web Notepad</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <style>

    </style>
</head>
<body>
<div id="container">

    <div id="left">
        <h1>Web Notepad</h1>
        <div class="user-info">User:</div>
        <div class="logout-div"><a class="logout-link" href="">Logout</a></div>
        <a class="notebook-add-link" href="/?action=notebookCreate">Add new notebook +</a>
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
            <p class="rename-p"><a class="notebook-rename-link" href="?action=notebookRename&notebookId=${currentNotebook.id}&notebookName=${currentNotebook.name}">Rename notebook</a></p>
            <p><a class="notebook-add-link" href="?action=noteCreate&notebookId=${currentNotebook.id}">Add new note +</a></p>
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
                    <input class="note-save-button" type="submit" form="content" value="Save note">
                </div>
                <div class="left-inline">
                    <a class="note-rename-link" href="?action=noteRename&noteId=${currentNote.id}&noteName=${currentNote.name}">Rename note</a>
                </div>
                <div class="left-inline">
                    <form method="post" action="note/delete">
                        <input type="hidden" name="noteId" value="${currentNote.id}">
                        <input class="note-delete-link" type="submit" value="Delete note">
                    </form>
                </div>
                <div class="left-inline">
                    <p class="note-last-modified">Last modified: ${currentNote.lastModified.toLocalDateTime()}</p>
                </div>
            </div>
            <div class="note-content">
                <form id="content" action="note/update" method="post">
                    <textarea style="width: 100%; height: 80%" name="content">${currentNote.content}</textarea>
                    <input type="hidden" name="noteId" value="${currentNote.id}">
                </form>
            </div>
        </c:if>
    </div>

    <c:if test="${param.get('action').equals('notebookCreate')}">
        <div class="change-cover">
            <h2 class="h2-change">CREATE NOTEBOOK</h2>
            <form method="post" action="/notebook/create">
                <input class="change-form-input" type="text" name="notebookName" value="">
                <input class="change-form-submit" type="submit" value="SAVE">
                <a class="cancel-button" href="/">Cancel</a>
            </form>
        </div>
    </c:if>

    <c:if test="${param.get('action').equals('notebookRename')}">
        <div class="change-cover">
            <h2 class="h2-change">RENAME</h2>
            <form method="post" action="/notebook/rename">
                <input type="hidden" name="notebookId" value="${param.get("notebookId")}">
                <input class="change-form-input" type="text" name="notebookNewName" value="${param.get("notebookName")}">
                <input class="change-form-submit" type="submit" value="SAVE">
                <a class="cancel-button" href="/">Cancel</a>
            </form>
        </div>
    </c:if>

    <c:if test="${param.get('action').equals('noteCreate')}">
        <div class="change-cover">
            <h2 class="h2-change">CREATE NOTE</h2>
            <form method="post" action="/note/create">
                <input class="change-form-input" type="text" name="noteName" value="">
                <input type="hidden" name="notebookId" value="${param.get("notebookId")}">
                <input class="change-form-submit" type="submit" value="SAVE">
                <a class="cancel-button" href="/">Cancel</a>
            </form>
        </div>
    </c:if>

    <c:if test="${param.get('action').equals('noteRename')}">
        <div class="change-cover">
            <h2 class="h2-change">RENAME</h2>
            <form method="post" action="/note/rename">
                <input type="hidden" name="noteId" value="${param.get("noteId")}">
                <input class="change-form-input" type="text" name="noteNewName" value="${param.get("noteName")}">
                <input class="change-form-submit" type="submit" value="SAVE">
                <a class="cancel-button" href="/">Cancel</a>
            </form>
        </div>
    </c:if>
</div>
</body>
</html>
