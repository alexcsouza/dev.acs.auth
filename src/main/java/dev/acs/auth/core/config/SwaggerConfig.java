package dev.acs.auth.core.config; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
//
//  @Bean
//  public Docket greetingApi() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("dev.acs"))
//        .paths(PathSelectors.regex("/.*"))
//        .build()
//        .apiInfo(metaData());
//  }

  @Bean
  public Docket api() {
    //Adding Header
//    ParameterBuilder aParameterBuilder = new ParameterBuilder();
//    aParameterBuilder.name("Authorization")                 // name of header
//            .modelRef(new ModelRef("string"))
//            .parameterType("header")
//            //.defaultValue("Basic em9uZTpteXBhc3N3b3Jk")        // based64 of - zone:mypassword
//            .required(true)                // for compulsory
//            .build();
//    java.util.List<Parameter> aParameters = new ArrayList<>();
//    aParameters.add(aParameterBuilder.build());             // add parameter
    return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("dev.acs"))
            .paths(PathSelectors.ant("/**"))
            .build()
            .apiInfo(metaData())

            //.pathMapping("/api/**")
//            .globalOperationParameters(aParameters)
            ;
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("ACS Auth REST API")
        .description("Auth API")
        .version("0.0.1-SNAPSHOT")
        .license("GNU GENERAL PUBLIC LICENSE - Version 3")
        .licenseUrl("https://www.gnu.org/licenses/gpl-3.0.txt")
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
