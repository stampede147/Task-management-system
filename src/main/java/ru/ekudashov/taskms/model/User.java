package ru.ekudashov.taskms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String firstName;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection()
    @CollectionTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    @Enumerated(value = EnumType.STRING)
    private Set<Role> authorities;
}
