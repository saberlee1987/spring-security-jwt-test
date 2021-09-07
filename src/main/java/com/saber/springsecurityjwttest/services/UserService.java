package com.saber.springsecurityjwttest.services;

import com.saber.springsecurityjwttest.dto.UserDto;
import com.saber.springsecurityjwttest.entities.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserPrincipal addUser(UserDto userDto);
}
