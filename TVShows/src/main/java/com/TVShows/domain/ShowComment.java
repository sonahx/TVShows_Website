package com.TVShows.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "show_comment")
@NoArgsConstructor @Getter @Setter
public class ShowComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Lob
    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tv_show_id")
    private TvShow tvShow;

    @Column
    private LocalDateTime date;

    public ShowComment(String text, User author, TvShow tvShow, LocalDateTime date) {
        this.text = text;
        this.author = author;
        this.tvShow = tvShow;
        this.date = date;
        this.tvShow.getComments().add(this);
    }
}
