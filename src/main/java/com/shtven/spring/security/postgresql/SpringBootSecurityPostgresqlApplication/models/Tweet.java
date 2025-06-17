package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table( name = "tweets")
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 140)
    private String tweet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posted_by", referencedColumnName = "id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User postedBy;

    public Tweet() {
    }

    public  Tweet (String tweet) {
        this.tweet = tweet;
    }

    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTweet() {
        return tweet;
    }
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

}
