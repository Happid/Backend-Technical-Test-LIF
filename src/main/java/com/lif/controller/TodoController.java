package com.lif.controller;

import com.lif.model.dto.ApiResponse;
import com.lif.model.dto.TodoRequest;
import com.lif.model.dto.TodoResponse;
import com.lif.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ApiResponse<TodoResponse> createTodo(
            @Valid @RequestBody TodoRequest request
    ) {
        TodoResponse response = todoService.createTodo(request);
        return ApiResponse.success("Todo created", response);
    }

    @GetMapping
    public ApiResponse<List<TodoResponse>> getTodos() {
        return ApiResponse.success(
                "Todo list retrieved",
                todoService.getMyTodos()
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<TodoResponse> updateTodo(
            @PathVariable Long id,
            @Valid @RequestBody TodoRequest request
    ) {
        TodoResponse response = todoService.updateTodo(id, request);
        return ApiResponse.success("Todo updated", response);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ApiResponse.success("Todo deleted", null);
    }

}
