package com.notepad.web.repository;

import com.notepad.web.entity.Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Notebook, Integer> {

    List<Notebook> findAllByUserId(Integer userId);

    @Modifying
    @Query("UPDATE Notebook n SET n.name=:newName WHERE n.id=:id AND n.userId=:userId")
    void rename(@Param("newName") String notebookNewName, @Param("id") Integer id, @Param("userId") Integer userId);

    @Modifying
    @Query("DELETE FROM Notebook n WHERE n.id=:id AND n.userId=:userId")
    void delete(@Param("id") Integer id, @Param("userId") Integer userId);

    @Query("SELECT n FROM Notebook n WHERE n.id=:id AND n.userId=:userId")
    Notebook get(@Param("id") Integer id, @Param("userId") Integer userId);

    @Modifying
    @Query("DELETE FROM Notebook n WHERE n.userId=:userId")
    void deleteAllByUserId(@Param("userId") Integer userId);

}
