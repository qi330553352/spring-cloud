package com.example.qixin;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 创  建   时  间： 2018/8/22 22:08
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
@EnableAuthorizationServer  //配置授权服务
public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(Config.OAUTH_CLIENT_ID)
                .secret(Config.OAUTH_CLIENT_SECRET)
                .resourceIds(Config.RESOURCE_ID)
                .scopes(Config.SCOPES)
                .authorities("ROLE_USER")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .redirectUris("http://default-oauth-callback.com")
                .accessTokenValiditySeconds(60*30) // 30min
                .refreshTokenValiditySeconds(60*60*24); // 24h
    }
}
