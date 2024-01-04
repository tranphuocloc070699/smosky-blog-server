package com.smosky.blogserver;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Blog microservice REST API Documentation",
				description = "Smosky Blog microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Smosky",
						email = "smosky@smoky.com",
						url = "https://www.smosky.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.smosky.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Smosky Blog microservice REST API Documentation",
				url = "https://www.smosky.com/swagger-ui.html"
		)
)
public class BlogServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogServerApplication.class, args);
	}

}
