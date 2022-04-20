package com.notepad.web.repository;

import com.notepad.web.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    @Modifying
    @Query("UPDATE Note n SET n.name=:newName WHERE n.id=:id AND n.userId=:userId")
    void rename(@Param("id") Integer id, @Param("newName") String newName, @Param("userId") Integer userId);

    @Modifying
    @Query("UPDATE Note n SET n.content=:content, n.lastModified=:lastModified WHERE n.id=:id AND n.userId=:userId")
    void updateContent(@Param("id") Integer id, @Param("content") String content, @Param("userId") Integer userId,
                       @Param("lastModified") ZonedDateTime lastModified);

    @Modifying
    @Query("DELETE FROM Note n WHERE n.id=:id AND n.userId=:userId")
    void delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT n FROM Note n WHERE n.id=:id AND n.userId=:userId")
    Note get(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT n FROM Note n WHERE n.notebookId=:notebookId AND n.userId=:userId")
    List<Note> getAllByNotebookId(@Param("notebookId") Integer notebookId, @Param("userId") Integer userId);
}
