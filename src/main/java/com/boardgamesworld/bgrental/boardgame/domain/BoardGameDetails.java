package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "details")
public class BoardGameDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long detail_id;
    private String description;
    private int minPlayers;
    private int maxPlayers;
    private String author;
    private String publisher;
    @ElementCollection(targetClass = BoardGameType.class)
    @JoinTable(name = "detail_types", joinColumns = @JoinColumn(name = "detail_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "types")
    private Set<BoardGameType> types;
    @ElementCollection(targetClass = BoardGameCategory.class)
    @JoinTable(name = "detail_category", joinColumns = @JoinColumn(name = "detail_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "categories")
    private Set<BoardGameCategory> categories;

    public BoardGameDetails(String description,
                            int minPlayers,
                            int maxPlayers,
                            String author,
                            String publisher,
                            Set<BoardGameType> types,
                            Set<BoardGameCategory> categories) {
        this.detail_id = 0;
        this.description = description;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.author = author;
        this.publisher = publisher;
        this.types = types;
        this.categories = categories;
    }
}
