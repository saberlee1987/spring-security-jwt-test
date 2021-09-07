package com.saber.springsecurityjwttest.repositories;

import com.saber.springsecurityjwttest.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity,Integer> {
    Optional<PersonEntity> findByNationalCode(String nationalCode);

}
