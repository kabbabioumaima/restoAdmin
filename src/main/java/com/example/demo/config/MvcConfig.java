package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	public MvcConfig() {
		super();
	}

	@Bean
	public ClassLoaderTemplateResolver yourTemplateResolver() {
		ClassLoaderTemplateResolver configurer = new ClassLoaderTemplateResolver();
		configurer.setPrefix("templates/");
		configurer.setSuffix(".html");
		configurer.setTemplateMode(TemplateMode.HTML);
		configurer.setCharacterEncoding("UTF-8");
		configurer.setCacheable(false);
		configurer.setOrder(0); // this is important. This way spring //boot will listen to both places 0 and 1
		configurer.setCheckExistence(true);
		return configurer;
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html");
        registry.addViewController("/login.html");
    }
	
	@Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/**",
                        "/vendor/**",
                        "/dist/**",
                        "/img/**",
                        "/style/**",
                        "/resources/**",
                        "/script/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**"

                )
                .addResourceLocations(
                        "classpath:/static/vendor/",
                        "classpath:/static/js/",
                        "classpath:/static/img/",
                        "classpath:/static/style/",
                        "classpath:/static/script/",
                        "classpath:/static/fonts/",
                        "classpath:/static/dist/",
                        "classpath:/static/sass/",
                        "classpath:/resources/",
                		"classpath:/static/inc/");
    }
	
	@Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
