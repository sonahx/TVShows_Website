package com.TVShows.domain;

import com.TVShows.enums.ViewerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private Integer totalProgress;

    @OneToMany(mappedBy = "usersShowProgress", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SeasonProgress> seasonProgress = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ViewerStatus status;

    public UsersShowProgress(User user, TVShow tvShow, ViewerStatus status) {
        this.user = user;
        this.tvShow = tvShow;
        this.status = status;
    }

    public Integer getTotalProgress(){
        int summedSeasonProgresses = 0;
        for (SeasonProgress progress : seasonProgress) {
            summedSeasonProgresses = summedSeasonProgresses + progress.getProgress();
        }
        return summedSeasonProgresses;
    }
}
