package com.example.qixin.configuration;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/** 自定义生成授权码
 * 创  建   时  间： 2018/8/26 15:15
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
public class CustomJdbcAuthorizationCodeServices extends JdbcAuthorizationCodeServices {

    private RandomValueStringGenerator generator = new RandomValueStringGenerator();

    public CustomJdbcAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
        this.generator = new RandomValueStringGenerator(32);
    }


    public String createAuthorizationCode(OAuth2Authentication authentication) {
        String code = this.generator.generate();
        store(code, authentication);
        return code;
    }

}
