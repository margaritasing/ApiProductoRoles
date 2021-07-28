package com.example.productoapicrud.security;

import com.example.productoapicrud.security.auth.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebServiceSecurity extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;

    @Autowired
    public WebServiceSecurity(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


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
        return new BCryptPasswordEncoder(5);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(appUserService);
        return provider;
    }


}
