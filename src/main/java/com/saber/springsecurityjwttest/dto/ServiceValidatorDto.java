package com.saber.springsecurityjwttest.dto;

import lombok.Data;

@Data
public class ServiceValidatorDto {
    private String fieldName;
    private String messageError;
}
