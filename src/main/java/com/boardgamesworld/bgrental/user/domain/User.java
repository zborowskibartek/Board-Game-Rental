package com.boardgamesworld.bgrental.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "first_name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String firstName;

    @Column(name = "second_name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String secondName;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "nick", nullable = false, unique = true, columnDefinition = "TEXT")
    private String nick;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
}