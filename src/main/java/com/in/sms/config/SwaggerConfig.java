package com.in.sms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customConfig() {
        return new OpenAPI()
                .info(
                        new Info().title("College APIs").description("By Xyz"))
                .servers(List.of(
                        new Server().url("http://localhost:8080/").description("live"),
                        new Server().url("http://localhost:8081/").description("local")
                ));
    }
}
