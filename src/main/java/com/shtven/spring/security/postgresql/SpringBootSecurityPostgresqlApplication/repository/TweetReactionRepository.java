package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.EReaction;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetReaction;

import java.util.Optional;

@Repository
public interface TweetReactionRepository extends JpaRepository<TweetReaction, Long> {
    Optional<TweetReaction> findByTweetAndUser(Tweet tweet, User user);
    long countByTweetAndReaction(Tweet tweet, EReaction reaction);
    Long countByTweetAndReaction_Description(Tweet tweet, EReaction description);


}
