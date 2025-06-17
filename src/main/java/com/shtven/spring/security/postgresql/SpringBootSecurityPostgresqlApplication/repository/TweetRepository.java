package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Tweet;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
