package com.coupon.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Coupon API",
                description = "API responsável pelo gerenciamento de cupons",
                version = "v1",
                contact = @Contact(
                        name = "Jorge Campos",
                        email = "jorge@email.com"
                )
        )
)
public class OpenApiConfig {
}
