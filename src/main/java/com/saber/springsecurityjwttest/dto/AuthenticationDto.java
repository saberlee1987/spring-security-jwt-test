package com.saber.springsecurityjwttest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
@ApiModel(value = "authentication")
public class AuthenticationDto {
    @NotBlank(message = "username is Required")
    @ApiModelProperty(position = 1,name = "username",value = "username",example = "saber66",required = true)
    private String username;
    @NotBlank(message = "password is Required")
    @ApiModelProperty(position = 2,name = "password",value = "password",required = true,example = "saber1366")
    private String password;
}
