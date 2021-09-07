package com.saber.springsecurityjwttest.dto;

import com.saber.springsecurityjwttest.entities.PersonEntity;
import lombok.Data;

import java.util.List;
@Data
public class PersonResponse {
    private List<PersonEntity> persons ;
}
