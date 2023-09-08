package com.example.todo_back_end_app.service;


import com.example.todo_back_end_app.model.TodoEntity;
import com.example.todo_back_end_app.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity entity) {
        // 1. validate
        validate(entity);

        // 2. get TodoEntity by id
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            // 3. put every data of entity on TodoEntity
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            // 4. save
            repository.save(todo);
        });

        // 5. return Todo-List by retrieve
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> delete(final TodoEntity entity) {
        // 1. validate
        validate(entity);

        try {
            // 2. delete entity
            repository.delete(entity);
        } catch (Exception e) {
            // 3. logging
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }

        // 4. return Todo-list
        return retrieve(entity.getUserId());
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
