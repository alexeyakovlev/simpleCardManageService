package ru.yakovlev.simplerestapi.models;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by alexi on 02.07.2025
 */
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
