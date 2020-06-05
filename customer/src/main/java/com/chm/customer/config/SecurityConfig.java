package com.chm.customer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/visitor/**").permitAll()
                .antMatchers("/visitor_data/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/cus/login").permitAll()
                .antMatchers("/customer/**").hasRole("CUS")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/visitor/index")
                .loginProcessingUrl("/cus/login")
                .defaultSuccessUrl("/customer/personal")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write(new ObjectMapper().writeValueAsString(authentication.getPrincipal()));
                        out.flush();
                        out.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        if (e instanceof BadCredentialsException) {
                            out.write(new ObjectMapper().writeValueAsString("用户或密码有误"));
                        }else {
                            out.write(new ObjectMapper().writeValueAsString(e.getMessage()));
                        }
                        out.flush();
                        out.close();
                    }
                })
                .and()
                .logout()
                .logoutUrl("/cus/logout")
                .logoutSuccessUrl("/visitor/index")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

   @Bean
    public PasswordEncoder createPasswordEncoder(){
       return new BCryptPasswordEncoder();
   }
}
