package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.EReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Reaction;

import java.util.Optional;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByDescription(EReaction tipo);

}
