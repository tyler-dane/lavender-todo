package com.netspi.interview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig {


    @Configuration
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        @Order(1)
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().cors().and().authorizeRequests().antMatchers("/").permitAll();
        }

    }
}
