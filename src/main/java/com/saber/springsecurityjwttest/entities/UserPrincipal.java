package com.saber.springsecurityjwttest.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
@Entity
@Table(name = "users")
@Data
public class UserPrincipal implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username",length = 50,unique = true)
    private String username;
    @Column(name = "password",length = 60)
    private String password;
    private boolean accountNoneExpired;
    private boolean accountNoneLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authority",
            joinColumns =@JoinColumn(name = "userId",referencedColumnName = "id") )
    private Collection<UserGrantedAuthority> authorities;

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNoneExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNoneLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
