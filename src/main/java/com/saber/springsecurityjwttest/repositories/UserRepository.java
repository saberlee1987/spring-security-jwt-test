package com.saber.springsecurityjwttest.repositories;

import com.saber.springsecurityjwttest.entities.UserPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserPrincipal,Integer> {
    Optional<UserPrincipal> findByUsername(String username);
}
