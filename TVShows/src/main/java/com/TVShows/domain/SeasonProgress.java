package com.TVShows.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor @Getter @Setter
public class SeasonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer progress = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Season_id")
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsersShowProgress_id")
    private UsersShowProgress usersShowProgress;

    public SeasonProgress (UsersShowProgress usersShowProgress, Season season){
        this.usersShowProgress = usersShowProgress;
        this.season = season;
    }

    public SeasonProgress (Integer progress, UsersShowProgress usersShowProgress, Season season){
        this.progress = progress;
        this.usersShowProgress = usersShowProgress;
        this.season = season;
    }
}
