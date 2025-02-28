package t221124nqt.ecommerce.hair_shop.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    private Server createServer(String url, String description) {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription(description);
        return server;
    }

    private Contact createContact() {
        return new Contact()
                .email("quangthai170402@gmail.com")
                .name("Thái Nguyễn quang")
                .url("https://quangthai.vn");
    }

    private License createLicense() {
        return new License()
                .name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");
    }

    private Info createApiInfo(String title, String version, String description) {
        return new Info()
                .title(title)
                .version(version)
                .contact(createContact())
                .description(description)
                .license(createLicense());
    }

    @Bean
    public OpenAPI myOpenAPI(
            @Value("${openapi.service.title}") String title,
            @Value("${openapi.service.version}") String version,
            @Value("${openapi.service.server}") String serviceUrl) {
        return new OpenAPI()
                .info(createApiInfo(title, version, "This API exposes all endpoints"))
                .servers(List.of(
                        createServer(serviceUrl, "Test server")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()));
    }

    // @Bean
    // public GroupedOpenApi myGroupedOpenApi() {
    //     return GroupedOpenApi.builder()
    //             .group("api-service")
    //             .pathsToMatch("/api/public/**")
    //             .packagesToScan("t221124nqt.ecommerce.hair_shop.controller")
    //             .build();
    // }
}
