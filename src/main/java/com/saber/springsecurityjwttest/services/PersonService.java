package com.saber.springsecurityjwttest.services;

import com.saber.springsecurityjwttest.dto.PersonDto;
import com.saber.springsecurityjwttest.dto.PersonResponse;
import com.saber.springsecurityjwttest.entities.PersonEntity;

public interface PersonService {
    PersonEntity addPerson(PersonDto personDto);
    PersonResponse findAll();
    PersonEntity findByNationalCode(String nationalCode);
}
