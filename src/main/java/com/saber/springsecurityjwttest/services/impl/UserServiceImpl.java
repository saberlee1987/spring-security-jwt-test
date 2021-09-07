package com.saber.springsecurityjwttest.services.impl;

import com.saber.springsecurityjwttest.dto.UserDto;
import com.saber.springsecurityjwttest.entities.UserPrincipal;
import com.saber.springsecurityjwttest.exceptions.ResourceExistException;
import com.saber.springsecurityjwttest.repositories.UserRepository;
import com.saber.springsecurityjwttest.services.JwtService;
import com.saber.springsecurityjwttest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private  UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public UserPrincipal addUser(UserDto userDto) {
        if (this.userRepository.findByUsername(userDto.getUsername()).isPresent()){
            throw new ResourceExistException(String.format("user with username %s is exist",userDto.getUsername()),"UserPrincipal");
        }

        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setUsername(userDto.getUsername());
        userPrincipal.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userPrincipal.setAccountNoneExpired(userDto.isAccountNoneExpired());
        userPrincipal.setAccountNoneLocked(userDto.isAccountNoneLocked());
        userPrincipal.setCredentialsNonExpired(userDto.isCredentialsNonExpired());
        userPrincipal.setEnabled(userDto.isEnable());
        userPrincipal.setAuthorities(userDto.getAuthorities());
        return this.userRepository.save(userPrincipal);

    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserPrincipal> optionalUserPrincipal = this.userRepository.findByUsername(username);
        if (optionalUserPrincipal.isPresent()){
          return optionalUserPrincipal.get();

        }
        throw new UsernameNotFoundException(String.format("user with username %s does not exist",username));
    }
}
