package com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.repository;

import java.util.Optional;

import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.ERole;
import com.shtven.spring.security.postgresql.SpringBootSecurityPostgresqlApplication.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}