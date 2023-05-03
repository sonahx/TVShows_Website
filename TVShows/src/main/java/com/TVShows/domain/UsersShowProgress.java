package com.TVShows.domain;

import com.TVShows.enums.ViewerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users_shows")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UsersShowProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    private TVShow tvShow;

    @Column
    private Integer episodeProgress;

    @Enumerated(EnumType.STRING)
    private ViewerStatus status;

    public UsersShowProgress(User user, TVShow tvShow,Integer episodeProgress, ViewerStatus status) {
        this.user = user;
        this.tvShow = tvShow;
        this.episodeProgress = episodeProgress;
        this.status = status;
    }
}
