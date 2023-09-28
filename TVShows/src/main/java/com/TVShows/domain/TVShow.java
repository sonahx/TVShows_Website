package com.TVShows.domain;

import com.TVShows.enums.ShowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;

@Entity
@Table(name = "TVShow")
@NoArgsConstructor @Getter @Setter
public class TVShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String originCountry;

    @Column
    private String originalName;

    @Column
    @ColumnDefault("0")
    private Integer episodesNumber;

    @Column
    private String releaseDate;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "Genre_id", nullable = false)
    private List<Genre> genre;

    @Column
    private Double voteAverage;

    @Column
    private Integer voteCount;

    @Lob
    private String directors;

    @Lob
    @Column(name = "description", length = 1000)
    private String description;

    @Lob
    @Column(name = "imageUrl", length = 1000)
    private String imageUrl;

    @Column
    private String trailerUrl;

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
    private Set<UsersShowProgress> userProgress = new HashSet<>();

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ShowComment> comments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TVShow tvShow = (TVShow) o;
        return getId() != null && Objects.equals(getId(), tvShow.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
