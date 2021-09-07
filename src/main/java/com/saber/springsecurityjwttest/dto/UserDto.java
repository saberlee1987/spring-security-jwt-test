package com.saber.springsecurityjwttest.dto;

import com.saber.springsecurityjwttest.entities.UserGrantedAuthority;
import lombok.Data;

import java.util.Set;
@Data
public class UserDto {
    private String username;
    private String password;
    private boolean accountNoneExpired;
    private boolean accountNoneLocked;
    private boolean credentialsNonExpired;
    private boolean enable;
    private Set<UserGrantedAuthority> authorities;
}
