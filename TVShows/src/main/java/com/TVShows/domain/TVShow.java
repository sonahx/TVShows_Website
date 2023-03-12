package com.TVShows.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TVShow")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Lob
    private String directors;
    @Lob
    @Column(name = "description", length = 1000)
    private String description;
    @Column
    private String imageUrl;
    @Column
    private String nextEpisode;
    @Enumerated(EnumType.STRING)
    private ShowStatus status;
    @Column
    private String episodeDuration;
    @Lob
    @Column(name = "actors", length = 1000)
    private String actors;
    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsersShows> shows = new HashSet<>();

    public TVShow(String name, String releaseDate, Genre genre, String directors, String description,
                  String imageUrl, String nextEpisode, ShowStatus status, String episodeDuration, String actors) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.directors = directors;
        this.description = description;
        this.imageUrl = imageUrl;
        this.nextEpisode = nextEpisode;
        this.status = status;
        this.episodeDuration = episodeDuration;
        this.actors = actors;
    }
}
