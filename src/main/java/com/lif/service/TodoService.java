package com.lif.service;

import com.lif.model.Todo;
import com.lif.model.User;
import com.lif.model.dto.TodoRequest;
import com.lif.model.dto.TodoResponse;
import com.lif.repository.TodoRepository;
import com.lif.repository.UserRepository;
import com.lif.config.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoService(
            TodoRepository todoRepository,
            UserRepository userRepository
    ) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    // ================= CREATE =================
    public TodoResponse createTodo(TodoRequest request) {
        User user = getCurrentUser();

        Todo todo = Todo.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .completed(false)
                .user(user)
                .build();

        Todo saved = todoRepository.save(todo);
        return mapToResponse(saved);
    }

    // ================= READ ALL =================
    public List<TodoResponse> getMyTodos() {
        User user = getCurrentUser();

        return todoRepository.findAllByUser(user)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ================= UPDATE =================
    public TodoResponse updateTodo(Long todoId, TodoRequest request) {
        User user = getCurrentUser();

        Todo todo = todoRepository.findByIdAndUser(todoId, user)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());

        return mapToResponse(todoRepository.save(todo));
    }

    // ================= DELETE =================
    public void deleteTodo(Long todoId) {
        User user = getCurrentUser();

        Todo todo = todoRepository.findByIdAndUser(todoId, user)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        todoRepository.delete(todo);
    }

    // ================= HELPER =================
    private User getCurrentUser() {
        String email = SecurityUtil.getCurrentUserEmail();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private TodoResponse mapToResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isCompleted()
        );
    }

}
