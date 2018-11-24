//package com.example.qixin.config;

/**
 * 创  建   时  间： 2018/11/24 12:25
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
//@Configuration
//public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
//    private final String adminContextPath;
//
//    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
//        this.adminContextPath = adminServerProperties.getContextPath();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
//        successHandler.setTargetUrlParameter("redirectTo");
//
//        http.authorizeRequests()
//                .antMatchers(adminContextPath + "/assets/**").permitAll()
//                .antMatchers(adminContextPath + "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
//                .logout().logoutUrl(adminContextPath + "/logout").and()
//                .httpBasic().and()
//                .csrf().disable();
//        // @formatter:on
//    }
//}
