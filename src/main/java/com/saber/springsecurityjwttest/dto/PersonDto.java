package com.saber.springsecurityjwttest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PersonDto {
    @NotBlank(message = "firstName is Required")
    private String firstName;
    @NotBlank(message = "lastName is Required")
    private String lastName;
    @NotBlank(message = "nationalCode is Required")
    @Size(min = 10,max = 10,message = "nationalCode must be 10 digits")
    @Pattern(regexp = "\\d+",message = "Please Enter Valid NationalCode")
    private String nationalCode;
    @NotNull(message = "age is Required")
    private Integer age;
    @NotBlank(message = "mobile is Required")
    @Size(min = 11,max = 11,message = "mobile must be 11 digits")
    @Pattern(regexp = "\\d+",message = "Please Enter Valid mobile")
    private String mobile;
}
