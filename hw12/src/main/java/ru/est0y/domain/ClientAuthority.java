package ru.est0y.domain;

import org.springframework.security.core.GrantedAuthority;

public class ClientAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "CLIENT";
    }
}
