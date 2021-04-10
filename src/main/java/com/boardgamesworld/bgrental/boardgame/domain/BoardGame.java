package com.boardgamesworld.bgrental.boardgame.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "boardgames")
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false)
    private long boardGameId;

    @Column(name = "name", nullable = false, updatable = false, columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE PRECISION")
    private double pricePerDay;

    @Column(name = "is_rented", nullable = false, columnDefinition = "BOOLEAN")
    private boolean rented;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition", nullable = false, columnDefinition = "VARCHAR(9)")
    private BoardGameCondition condition;

    @OneToOne(targetEntity = BoardGameDetails.class, cascade = CascadeType.ALL)
    private BoardGameDetails details;

}
