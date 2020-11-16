package com.example.PITime01.application.http.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final
    MyUserDetailService userDetailsService;

    public SecurityConfig(MyUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").authenticated()
                //.antMatchers("/employee/**").hasAnyAuthority(ADMIN.name())
                //.antMatchers("/union/**").hasAnyAuthority(ADMIN.name())
                //.antMatchers("/driver/**").hasAnyAuthority(ADMIN.name())
                .and().formLogin()
                .loginPage("/login")
                .permitAll();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){ return new BCryptPasswordEncoder(); }

}
