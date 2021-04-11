package com.boardgamesworld.bgrental.boardgame.infrastructure.database;

import com.boardgamesworld.bgrental.boardgame.domain.BoardGame;
import org.springframework.data.jpa.repository.JpaRepository;

interface BoardGameRepositorySQL extends JpaRepository<BoardGame, Long> {



}
