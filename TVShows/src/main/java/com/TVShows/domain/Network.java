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
public class Network {
    @Id
    private Integer id;

    @Column
    private String name;

    @Column
    private String origin_country;

    @ManyToMany(mappedBy = "networks")
    private List<TvShow> shows = new ArrayList<>();
}
