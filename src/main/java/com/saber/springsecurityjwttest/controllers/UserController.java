package com.saber.springsecurityjwttest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saber.springsecurityjwttest.dto.UserDto;
import com.saber.springsecurityjwttest.entities.UserPrincipal;
import com.saber.springsecurityjwttest.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private final ObjectMapper mapper;

    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPrincipal> addUser(@RequestBody @Valid UserDto userDto){

        try {
            log.info("Request for addUser with body ===> {}",mapper.writeValueAsString(userDto));
        }catch (Exception ex){
            log.error("Error for read data from input request");
        }

        UserPrincipal userPrincipal= this.userService.addUser(userDto);

        try {
            log.info("Response for addUser with body ===> {}",mapper.writeValueAsString(userPrincipal));
        }catch (Exception ex){
            log.error("Error for read data from response addUser");
        }
        return ResponseEntity.ok(userPrincipal);
    }
}
