package com.TVShows.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
public class SeasonProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer progress = 0;
    private Integer seasonId;
    private Integer SeasonNumber;
    private Integer maxProgress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UsersShowProgress_id")
    private UsersShowProgress usersShowProgress;

    public SeasonProgress(UsersShowProgress usersShowProgress, Integer seasonId) {
        this.usersShowProgress = usersShowProgress;
        this.seasonId = seasonId;
    }

    public SeasonProgress(Integer progress, UsersShowProgress usersShowProgress, Integer seasonId) {
        this.progress = progress;
        this.usersShowProgress = usersShowProgress;
        this.seasonId = seasonId;
    }
}
