package com.example.productoapicrud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.productoapicrud.security.UserPermission.PRODUCTO_WRITE;
import static com.example.productoapicrud.security.UserPermission.USER_READ;
import static com.example.productoapicrud.security.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebServiceSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/","/index.html").permitAll()
               // .antMatchers(HttpMethod.PUT, "/api/productos**").hasAuthority(PRODUCTO_WRITE.getPermission())
               // .antMatchers(HttpMethod.POST, "/api**").hasAuthority(PRODUCTO_WRITE.getPermission())
              //  .antMatchers(HttpMethod.DELETE, "/api/productos**").hasAnyRole(ADMIN.getRole())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
               // .formLogin(); Este tambien es un metodo , pero cuando se usa formulario (Frontend) se puede usar
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
                .authorities(ADMIN.getGrantedAuthorities())
                .roles("ADMIN")
                .build();

        UserDetails usuario2= User.builder()
                .username("Alexander")
                .password(passwordEncoder().encode("facilito"))
                .roles(UserRole.CLIENTE.name())
                .build();

        UserDetails usuario3= User.builder()
                .username("Angelina")
                .password(passwordEncoder().encode("facilongo"))
                .roles(ADMIN.name())
                .build();

        UserDetails usuario4 = User.builder()
                .username("test")
                .password(passwordEncoder().encode("password"))
                .authorities(UserRole.SELLER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2, usuario3, usuario4);


    }


}
