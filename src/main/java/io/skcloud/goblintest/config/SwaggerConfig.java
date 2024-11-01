package io.skcloud.goblintest.config;//package io.skcloud.goblinfulltest.config;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig implements ApplicationContextAware {
//
//	@Autowired
//	private ApplicationContext applicationContext;
////    @Bean
////    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
////        return new KeycloakSpringBootConfigResolver();
////    }
//	@Bean
//	public Docket localeApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("io.skcloud.goblinfulltest")).build()
//				.apiInfo(getApiInfo()).consumes(getConsumes()).produces(getProduces());
//	}
//
//	private ApiInfo getApiInfo() {
//		return new ApiInfoBuilder().title(applicationContext.getId())
//				.description("Goblin Full Test Rest API").termsOfServiceUrl("약관").license("라이센스")
//				.licenseUrl("").version("1.0.0").build();
//	}
//
//	private Set<String> getConsumes() {
//		Set<String> consumes = new HashSet<String>();
//		consumes.add("application/json");
//		return consumes;
//	}
//
//	private Set<String> getProduces() {
//		Set<String> produces = new HashSet<String>();
//		produces.add("application/json");
//		return produces;
//	}
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//		if (applicationContext instanceof ConfigurableApplicationContext) {
//			this.applicationContext = (ConfigurableApplicationContext) applicationContext;
//		}
//	}
//}
