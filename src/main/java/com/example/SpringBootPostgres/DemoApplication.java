package com.example.SpringBootPostgres;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(title="Springboot postgres DB + Internal DB Test", version="1.0.0"),
		servers = {@Server(url="http://localhost:8080"), @Server(url="http://localhost2:8080")},
		tags = {@Tag(name="PersonPostgresController", description = "this controller maintains postgres calls")}
)
@SecurityScheme(name="OurBearerJWT", type= SecuritySchemeType.HTTP, scheme = "bearer", in = SecuritySchemeIn.HEADER, bearerFormat = "JWT", description = "bearer JWT Token")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
