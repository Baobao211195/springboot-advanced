package com.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.*;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}admin@password").roles("ADMIN");

//       auth.jdbcAuthentication()
//           .dataSource(dataSource)
//           .usersByUsernameQuery("select id, username, password, enabled from users where username = ?")
//           .authoritiesByUsernameQuery("select id, userid, authority from authorities where userid = ?")
//           .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
            .and()
            .httpBasic(); // Use Basic authentication
    }
}
