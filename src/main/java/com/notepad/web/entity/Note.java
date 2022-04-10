package com.notepad.web.entity;

import java.time.ZonedDateTime;

public class Note {

    private Integer id;
    private Integer notebookId;
    private Integer userId;
    private String name;
    private String content;
    private ZonedDateTime lastModified;

    public Note() {
    }

    public Note(Integer id, Integer notebookId, Integer userId, String name, String content, ZonedDateTime lastModified) {
        this.id = id;
        this.notebookId = notebookId;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.lastModified = lastModified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Integer notebookId) {
        this.notebookId = notebookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
