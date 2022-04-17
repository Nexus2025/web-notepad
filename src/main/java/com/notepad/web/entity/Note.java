package com.notepad.web.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "notebook_id")
    private Integer notebookId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private String content;

    @Column(name = "last_modified")
    private ZonedDateTime lastModified;

    public Note() {
    }

    public Note(Integer notebookId, Integer userId, String name) {
        this.notebookId = notebookId;
        this.userId = userId;
        this.name = name;
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
