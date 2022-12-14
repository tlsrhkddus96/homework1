package com.example.homework1.config;

import com.example.homework1.security.service.DetailService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http.authorizeRequests()
                        .antMatchers("/member/my").hasRole("USER")
                        .antMatchers("/member/modify").hasRole("USER");

        http.csrf().disable();
        http.formLogin()
                .loginPage("/member/123login")
                .loginProcessingUrl("/member/123login")
                .defaultSuccessUrl("/index");
        http.logout().logoutSuccessUrl("/member/123login");

    }






}
