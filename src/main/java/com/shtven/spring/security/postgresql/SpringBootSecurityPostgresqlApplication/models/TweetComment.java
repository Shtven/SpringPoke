package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tweet_comments")
public class TweetComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tweet_id")
    @JsonIgnoreProperties({"comments", "reactions", "author"})
    private Tweet tweet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"tweets", "comments", "reactions", "password"})
    private User author;


    public TweetComment() {}

    public TweetComment(String content, Tweet tweet, User author) {
        this.content = content;
        this.tweet = tweet;
        this.author = author;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Tweet getTweet() { return tweet; }
    public void setTweet(Tweet tweet) { this.tweet = tweet; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
}
