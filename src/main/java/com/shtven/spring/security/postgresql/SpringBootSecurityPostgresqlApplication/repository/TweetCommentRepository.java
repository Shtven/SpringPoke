package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.TweetComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetCommentRepository extends JpaRepository <TweetComment, Long> {

    List<TweetComment> findAllByTweetId(@Param("tweetId")Long tweetId);
}
