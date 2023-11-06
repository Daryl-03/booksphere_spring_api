package dev.richryl.booksphere.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

	public OpenAPI bookInfoOpenApi() {
		return new OpenAPI()
				.info(
						new Info()
								.title("Book Crud rest api")
								.description("This api provides with information about the endpoints of the book api")
								.version("1.0")
				);
	}
}
