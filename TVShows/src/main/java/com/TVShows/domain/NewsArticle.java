package com.TVShows.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NewsArticle")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class NewsArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String relatedTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Lob
    @Column(name = "text", length = 10000, nullable = false)
    private String text;

    @Column(nullable = false)
    private LocalDateTime date;

    @Lob
    @Column(name = "imageUrl", length = 200, nullable = false)
    private String imageUrl;

    @OneToMany(mappedBy = "newsArticle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NewsArticleComment> comments = new ArrayList<>();
}
