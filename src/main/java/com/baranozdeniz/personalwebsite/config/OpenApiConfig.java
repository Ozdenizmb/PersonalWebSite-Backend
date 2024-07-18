package com.baranozdeniz.personalwebsite.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI utilityOpenAPI(
            @Value("${openapi.app.description}") String description,
            @Value("${openapi.app.version}") String version,
            @Value("${openapi.app.title}") String title,
            @Value("${openapi.app.contact}") String contactName,
            @Value("${openapi.app.email}") String email,
            @Value("${openapi.app.licence}") String licenseName,
            @Value("${openapi.app.url}") String url
    ) {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("basic")
                        )
                )
                .info(new Info().title(title).description(description).version(version)
                        .contact(new Contact().name(contactName).email(email))
                        .license(new License().name(licenseName).url(url))
                )
                .addSecurityItem(new SecurityRequirement().addList("basicAuth"));
    }
}
