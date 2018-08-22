package com.example.qixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * 创  建   时  间： 2018/8/22 22:11
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
@EnableWebSecurity  //开启安全认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }


    @Override // 配置默认登陆页面，登陆失败页面
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable() //关闭CSRF
                .formLogin()
                .loginPage("/login")
                .failureUrl("/oauth/authorize")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic().realmName("OAuth Server");

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/oauth/authorize").authenticated()
//                .and()
//                .httpBasic().realmName("OAuth Server");
//    }
}
