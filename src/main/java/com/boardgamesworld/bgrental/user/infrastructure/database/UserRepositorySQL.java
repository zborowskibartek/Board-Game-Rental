package com.boardgamesworld.bgrental.user.infrastructure.database;

import com.boardgamesworld.bgrental.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepositorySQL extends JpaRepository<User, Long> {
}
