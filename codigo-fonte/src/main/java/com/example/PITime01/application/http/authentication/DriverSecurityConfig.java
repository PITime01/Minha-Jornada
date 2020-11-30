package com.example.PITime01.application.http.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(2)
public class DriverSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    final DriverDetailsService driverDetailsService;

    public DriverSecurityConfig(DriverDetailsService driverDetailsService) {
        this.driverDetailsService = driverDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(driverDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/driverdash").authenticated()
                .and().formLogin()
                .loginPage("/driver/login").defaultSuccessUrl("/driverdash", true)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/driver/login")
                .permitAll();

    }

}
