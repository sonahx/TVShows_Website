package com.TVShows.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NewsArticleComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Lob
    @Column(name = "text", length = 1000, nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "NewsArticle_id")
    private NewsArticle newsArticle;

    public NewsArticleComment(String text, User author, LocalDateTime date, NewsArticle newsArticle) {
        this.text = text;
        this.author = author;
        this.date = date;
        this.newsArticle = newsArticle;
    }
}
