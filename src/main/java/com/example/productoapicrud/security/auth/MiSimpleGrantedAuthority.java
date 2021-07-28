package com.example.productoapicrud.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.jackson2.SimpleGrantedAuthorityMixin;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="authorities")
public class MiSimpleGrantedAuthority implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authority_id;
    @Column(nullable = false)
    private String role;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private Set<AppUser> usuarios;

    public MiSimpleGrantedAuthority(String role) {
        this.role = role;
    }
    public MiSimpleGrantedAuthority() {
    }

    public String getAuthority() {
        return this.role;
    }

    public String toString() {
        return this.role;
    }

    public void setAuthority_id(Integer authority_id) {
        this.authority_id = authority_id;
    }

    public Integer getAuthority_id() {
        return authority_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<AppUser> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<AppUser> usuarios) {
        this.usuarios = usuarios;
    }
}