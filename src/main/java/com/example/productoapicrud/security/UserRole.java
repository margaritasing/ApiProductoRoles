package com.example.productoapicrud.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.productoapicrud.security.UserPermission.*;

public enum UserRole {

    CLIENTE(Set.of(USER_READ, PRODUCTO_READ)),
    SELLER(Set.of(USER_READ, USER_WRITE)),
    ADMIN(Set.of(USER_READ, USER_WRITE, PRODUCTO_READ, PRODUCTO_WRITE));

    private final Set<UserPermission> permissions;


    UserRole(Set<UserPermission> permissions) {
        this.permissions =permissions;
    }

    // Probar este get en Postman
   public String getRole(){
        return this.name();
   }

    public Set<UserPermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getPermissions().stream()
                .map(permissions -> new SimpleGrantedAuthority(permissions.getPermission()))
                .collect(Collectors.toSet());

        permisos.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisos;
    }



}
