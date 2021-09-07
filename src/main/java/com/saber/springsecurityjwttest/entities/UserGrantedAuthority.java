package com.saber.springsecurityjwttest.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@Embeddable
public class UserGrantedAuthority implements GrantedAuthority {
    @Column(name = "authority",length = 15)
     private String authority;
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
