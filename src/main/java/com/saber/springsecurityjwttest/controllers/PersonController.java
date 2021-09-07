package com.saber.springsecurityjwttest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.springsecurityjwttest.dto.PersonDto;
import com.saber.springsecurityjwttest.entities.PersonEntity;
import com.saber.springsecurityjwttest.services.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/persons")
@Slf4j
public class PersonController {
    @Autowired
    private ObjectMapper mapper;
    private final PersonService personService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonEntity> addPerson(@RequestBody @Valid PersonDto personDto) {

        try {
            log.info("Request for add person with body ===> {}",mapper.writeValueAsString(personDto));
        }catch (Exception ex){
            log.error("Error for read data from input request");
        }
        PersonEntity entity = this.personService.addPerson(personDto);
        try {
            log.info("Response for add person with body ===> {}",mapper.writeValueAsString(entity));
        }catch (Exception ex){
            log.error("Error for read data from response addPerson");
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(value = "/findByNationalCode/{nationalCode}")
    public ResponseEntity<PersonEntity> findByNationalCode(@PathVariable @NotBlank(message = "nationalCode is Required")
                                                           @Size(min = 10, max = 10, message = "nationalCode must be 10 digits")
                                                           @Pattern(regexp = "\\d+", message = "Please Enter valid nationalCode")
                                                                   String nationalCode) {
        PersonEntity entity = this.personService.findByNationalCode(nationalCode);
        try {
            log.info("Response for add person with body ===> {}",mapper.writeValueAsString(entity));
        }catch (Exception ex){
            log.error("Error for read data from response findByNationalCode");
        }
        return ResponseEntity.ok(entity);
    }

}
