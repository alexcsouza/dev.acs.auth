package dev.acs.auth.core.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket greetingApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("dev.acs"))
        .paths(PathSelectors.regex("/.*"))
        .build()
        .apiInfo(metaData());

  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("ACS Auth REST API")
        .description("Auth API")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
        .build();
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
}


// @RestController
// @Api(value = "Greeting", description = "Greeting people")
// public class GreetingController {

//   @ApiOperation(value = "Greets the world or Niteroi")
//   @GetMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//   public Greeting hello(@RequestParam(required = false) boolean niteroi) {
//     Greeting greeting = new Greeting("Hello world");
//     if (niteroi) {
//       greeting.setMessage(greeting.getMessage().replace("world", "Niteroi"));
//     }
//     return greeting;
//   }

//   @ApiOperation(value = "Greets a person given her name")
//   @GetMapping(value = "/hello/{name}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//   public Greeting get(@PathVariable String name) {
//     return new Greeting("Hello, " + name);
//   }
//}
