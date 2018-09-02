package com.example.qixin;

import com.example.qixin.configuration.CustomJdbcAuthorizationCodeServices;
import com.example.qixin.configuration.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * 创  建   时  间： 2018/8/22 22:08
 * 版           本: V1.0
 * 作           者: qixin
 * 版  权   所  有: 版权所有(C)2016-2026
 */
@Configuration
@EnableAuthorizationServer  //配置授权服务
public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AuthorizationEndpoint authorizationEndpoint;

    @Bean
    public TokenStore tokenStore() {

        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public ApprovalStore approvalStore() {

        return new JdbcApprovalStore(dataSource);
    }

    @Bean 	//使用默认的授权码
    protected AuthorizationCodeServices authorizationCodeServices() {

        return new CustomJdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource); // oauth_client_details
    }

    @Override //解决Spring Security OAuth在访问/oauth/token时候报401 authentication is required
    public void configure(AuthorizationServerSecurityConfigurer oauthServer)throws Exception {

        oauthServer.allowFormAuthenticationForClients();
    }

    //---------3---------------- 自定义页面

    @PostConstruct
    public void init() {
        authorizationEndpoint.setUserApprovalPage("forward:/oauth/my_approval_page");
        authorizationEndpoint.setErrorPage("forward:/oauth/my_error_page");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.approvalStore(approvalStore()) 							// oauth_approvals
                .authorizationCodeServices(authorizationCodeServices()) 	// oauth_code
                .tokenEnhancer(new CustomTokenEnhancer())
                .tokenStore(tokenStore()); 								// oauth_access_token & oauth_refresh_token
    }

}
