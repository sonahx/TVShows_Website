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
public class Author {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer gender;

    @ManyToMany(mappedBy = "authors")
    private List<TVShow> shows = new ArrayList<>();
}
