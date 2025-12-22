package com.lif.model;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Todo> todos;

    public @Nullable String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }
}
