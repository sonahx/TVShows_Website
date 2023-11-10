package com.TVShows.domain;

import com.TVShows.enums.ShowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

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
    private String origin_country;

    @Column
    private String originalName;

    @Column
    private String releaseDate;

    @Column
    private String voteAverage;

    @Column
    private Integer voteCount;

    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    @Lob
    @Column(name = "imageUrl", length = 1250)
    private String imageUrl;

    @Column
    private String show_status;

    @Column
    private Boolean adult;

    @Column
    private String homepage;

    @Column
    private Boolean in_production;

    @Column
    private List<String> languages;

    @Column
    private String last_air_date;

    @Column
    private Integer number_of_episodes;

    @Column
    private Integer number_of_seasons;

    @Column
    private String trailerUrl;

    @Lob
    @Column(name = "overview", length = 1000)
    private String overview;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "Genre_id", nullable = false)
    private List<Genre> genre;

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "Author_id", nullable = false)
    private List<Author> authors = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinColumn(name = "Network_id", nullable = false)
    private List<Network> networks = new ArrayList<>();

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Season> seasons = new ArrayList<>();

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<UsersShowProgress> userProgress = new HashSet<>();

    @OneToMany(mappedBy = "tvShow", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
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
