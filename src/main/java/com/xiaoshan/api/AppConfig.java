package com.xiaoshan.api;

import com.xiaoshan.common.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableWebSecurity
@EnableJpaRepositories(basePackages = "com.xiaoshan.dal")
@ComponentScan(basePackages = "com.xiaoshan.*")
@EntityScan(basePackages = "com.xiaoshan.domain", basePackageClasses = {AppConfig.class, Jsr310JpaConverters.class})
public class AppConfig extends WebSecurityConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(AppConfig.class, args);
        System.out.println("running success");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .useDefaultResponseMessages(false)
                .globalOperationParameters(parameters());
    }

    private List<Parameter> parameters() {
        List<Parameter> parameters = new ArrayList<>();
        ParameterBuilder parameterBuilder = new ParameterBuilder()
                .name("Authorization")
                .description("权限验证")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false);
        parameters.add(parameterBuilder.build());
        return parameters;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("测土配方api")
                .description("")
                .version("1.0")
                .build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .addFilterBefore(getMyFilter(), BasicAuthenticationFilter.class);
//				.and()
//				.authorizeRequests()
//				.antMatchers("/api/**").authenticated()
//				.antMatchers("/swagger-ui.html", "/").
    }

    public AuthenticationFilter getMyFilter(){
        return new AuthenticationFilter();
    }

    //    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
}
