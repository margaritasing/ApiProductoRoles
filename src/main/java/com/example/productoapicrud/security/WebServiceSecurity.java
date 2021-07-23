package com.example.productoapicrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebServiceSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/index.html").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService(){


        UserDetails usuario1= User.builder()
                .username("Libia")
                .password(passwordEncoder().encode("facil"))
                .roles("ADMIN")
                .build();

        UserDetails usuario2= User.builder()
                .username("Alexander")
                .password(passwordEncoder().encode("facilito"))
                .roles("CLIENTE")
                .build();

        UserDetails usuario3= User.builder()
                .username("Angelina")
                .password(passwordEncoder().encode("facilongo"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3);


    }


}
