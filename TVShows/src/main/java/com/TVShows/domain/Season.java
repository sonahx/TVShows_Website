package com.TVShows.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor @Getter @Setter
public class Season {
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Integer episode_count;

    @Column
    private Integer season_number;

    @Column
    private Double vote_average;

    @Column
    private String poster_path;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private TVShow tvShow;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeasonProgress> seasonProgress = new ArrayList<>();
}
