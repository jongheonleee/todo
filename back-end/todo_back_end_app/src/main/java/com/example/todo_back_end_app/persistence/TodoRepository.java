package com.example.todo_back_end_app.persistence;


import com.example.todo_back_end_app.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    @Query("select * from TodoEntity t where t.userId = ?1")
    List<TodoEntity> findByUserId(String userId);

}
