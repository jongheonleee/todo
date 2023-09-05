package com.example.todo_back_end_app.controller;


import com.example.todo_back_end_app.dto.ResponseDTO;
import com.example.todo_back_end_app.dto.TodoDTO;
import com.example.todo_back_end_app.model.TodoEntity;
import com.example.todo_back_end_app.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testService() {
        String str = service.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user";

            // 1. dto -> entity
            TodoEntity entity = TodoDTO.toEntity(dto);

            // 2. set null on id in entity for initialize
            entity.setId(null);

            // 3. set userid on userId in entity for initialize
            entity.setUserId(temporaryUserId);

            // 4. create Todo entity by service
            List<TodoEntity> entities = service.create(entity);

            // 5. list of entity -> list of TodoDTO
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // 6. initialize ResponseDTO by TodoDTO
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // 7. return ResponseDTO
            return ResponseEntity.ok().body(response);

        } catch(Exception e) {

            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }

    }
}
