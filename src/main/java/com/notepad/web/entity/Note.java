package com.notepad.web.entity;

public class Note {

    private Integer id;
    private Integer notebookId;
    private Integer userId;
    private String name;
    private String content;

    public Note() {
    }

    public Note(Integer id, Integer notebookId, Integer userId, String name, String content) {
        this.id = id;
        this.notebookId = notebookId;
        this.userId = userId;
        this.name = name;
        this.content = content;
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
}
