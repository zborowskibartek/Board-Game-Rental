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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long detailId;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "min_players", nullable = false, columnDefinition = "INT8")
    private int minPlayers;

    @Column(name = "max_players", nullable = false, columnDefinition = "INT8")
    private int maxPlayers;

    @Column(name = "author", nullable = false, columnDefinition = "VARCHAR(50)")
    private String author;

    @Column(name = "publisher", nullable = false, columnDefinition = "VARCHAR(50)")
    private String publisher;

    @ElementCollection(targetClass = BoardGameType.class)
    @JoinTable(name = "detail_types", joinColumns = @JoinColumn(name = "detail_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "types_id", nullable = false)
    private Set<BoardGameType> types;

    @ElementCollection(targetClass = BoardGameCategory.class)
    @JoinTable(name = "detail_category", joinColumns = @JoinColumn(name = "detail_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "categories_id", nullable = false)
    private Set<BoardGameCategory> categories;

    public BoardGameDetails(String description,
                            int minPlayers,
                            int maxPlayers,
                            String author,
                            String publisher,
                            Set<BoardGameType> types,
                            Set<BoardGameCategory> categories) {
        this.description = description;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.author = author;
        this.publisher = publisher;
        this.types = types;
        this.categories = categories;
    }
}
