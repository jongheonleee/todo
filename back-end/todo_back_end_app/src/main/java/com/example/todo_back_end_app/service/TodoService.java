package com.example.todo_back_end_app.service;


import com.example.todo_back_end_app.model.TodoEntity;
import com.example.todo_back_end_app.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
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

    public List<TodoEntity> create(final TodoEntity entity) {
        // validation
        validate(entity);

        // save
        repository.save(entity);

        // return
        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }
}
