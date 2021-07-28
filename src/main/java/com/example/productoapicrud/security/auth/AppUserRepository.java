package com.example.productoapicrud.security.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    AppUser findAppUserByUsername(String userName);
}
