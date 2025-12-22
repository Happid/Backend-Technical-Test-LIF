package com.lif.repository;

import com.lif.model.Todo;
import com.lif.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>{

    List<Todo> findAllByUser(User user);
    Optional<Todo> findByIdAndUser(Long id, User user);

}
