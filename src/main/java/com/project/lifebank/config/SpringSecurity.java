package com.project.lifebank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/index**","/","/js/**","/css/**", "/imgs/**", "/register")
                .permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/doctor/**").hasAuthority( "DOCTOR")
                .requestMatchers("/donor/**").hasAuthority("DONOR")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin(
                        form ->form
                                .loginPage("/login")
                                .usernameParameter("email")
                                .successHandler((request, response, authentication) -> {
                                    UserDetails user = (UserDetails) authentication.getPrincipal();
                                    boolean isAdmin = user.getAuthorities().stream()
                                            .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
                                    boolean isDonor = user.getAuthorities().stream()
                                            .anyMatch(auth -> auth.getAuthority().equals("DONOR"));
                                    boolean isDoctor = user.getAuthorities().stream()
                                            .anyMatch(auth -> auth.getAuthority().equals("DOCTOR"));
                                    if (isAdmin) {
                                        response.sendRedirect("/admin");
                                    } else if(isDonor){
                                        response.sendRedirect("/donor");
                                    }else if(isDoctor){
                                        response.sendRedirect("/doctor");
                                    }else{
                                        response.sendRedirect("/");
                                    }
                                })

                                .permitAll()
                );
        return http.build();

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    }