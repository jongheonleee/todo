package com.example.todo_back_end_app.service;


import com.example.todo_back_end_app.model.TodoEntity;
import com.example.todo_back_end_app.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder()
                                .title("my first todo item")
                                .build();

        repository.save(entity);

        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();


    }
}
