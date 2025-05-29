package com.example.supplychain.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development Server");

        Contact contact = new Contact();
        contact.setName("Your Name");
        contact.setEmail("your@email.com");

        Info info = new Info()
                .title("Digital Supply Chain API")
                .version("1.0")
                .description("API documentation for the Digital Supply Chain project.")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
