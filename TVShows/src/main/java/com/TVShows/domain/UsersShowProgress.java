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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersShowProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer totalProgress;
    private Integer currentProgress;
    private Integer showId;
    private Integer personalScore;
    private String showName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ViewerStatus status;

    @OneToMany(mappedBy = "usersShowProgress", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SeasonProgress> seasonProgress = new ArrayList<>();

    public UsersShowProgress(User user, Integer showId, ViewerStatus status) {
        this.user = user;
        this.showId = showId;
        this.status = status;
    }

    public UsersShowProgress(User user, Integer showId, String showName, Integer totalProgress, ViewerStatus status) {
        this.user = user;
        this.showId = showId;
        this.showName = showName;
        this.totalProgress = totalProgress;
        this.status = status;
    }

    public void setPersonalScore(Integer score) {
        if (score >= 1 && score <= 10) {
            this.personalScore = score;
        }
    }
}
