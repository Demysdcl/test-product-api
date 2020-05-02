package com.wipro.productApi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${app.client.id}")
    private String clientId;
    @Value("${app.client.secret}")
    private String secret;
    @Value("${host.full.dns.auth.link}")
    private String authLink;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wipro.productApi.context"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private OAuth securitySchema() {
        List<AuthorizationScope> authorizationScopes = Arrays.asList(getAuthorizationScopes());
        List<GrantType> grantTypes = Arrays
                .asList(new ResourceOwnerPasswordCredentialsGrant(authLink+"/oauth/token"));
        return new OAuth("oauth2schema", authorizationScopes, grantTypes);
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/products/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        return Collections
                .singletonList(new SecurityReference("oauth2schema", getAuthorizationScopes()));
    }

    private AuthorizationScope[] getAuthorizationScopes() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[2];
        authorizationScopes[0] = new AuthorizationScope("read", "Ler Tudo");
        authorizationScopes[1] = new AuthorizationScope("write", "Acessar tudo");
        return authorizationScopes;
    }

    @Bean
    public SecurityConfiguration securityConfiguration() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(secret)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }
}
