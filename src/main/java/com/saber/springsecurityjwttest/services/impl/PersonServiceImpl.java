package com.saber.springsecurityjwttest.services.impl;

import com.saber.springsecurityjwttest.dto.PersonDto;
import com.saber.springsecurityjwttest.dto.PersonResponse;
import com.saber.springsecurityjwttest.entities.PersonEntity;
import com.saber.springsecurityjwttest.exceptions.ResourceExistException;
import com.saber.springsecurityjwttest.exceptions.ResourceNotFoundException;
import com.saber.springsecurityjwttest.repositories.PersonRepository;
import com.saber.springsecurityjwttest.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    @Transactional
    public PersonEntity addPerson(PersonDto personDto) {
        if (this.personRepository.findByNationalCode(personDto.getNationalCode()).isPresent()) {
            throw new ResourceExistException(String.format("Person with nationalCode %s exist",
                personDto.getNationalCode()),"Person");
        }
        PersonEntity entity =new PersonEntity();
        entity.setFirstName(personDto.getFirstName());
        entity.setLastName(personDto.getLastName());
        entity.setAge(personDto.getAge());
        entity.setMobile(personDto.getMobile());
        entity.setNationalCode(personDto.getNationalCode());
        return this.personRepository.save(entity);

    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse findAll() {
        PersonResponse response = new PersonResponse();
        List<PersonEntity> persons =  this.personRepository.findAll();
        response.setPersons(persons);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public PersonEntity findByNationalCode(String nationalCode) {
        Optional<PersonEntity> optionalPersonEntity =this.personRepository.findByNationalCode(nationalCode);
        if (optionalPersonEntity.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Person with nationalCode %s does not exist",
                    nationalCode),"Person");
        }
        return optionalPersonEntity.get();
    }
}
