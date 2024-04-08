package com.github.meetingweb.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.reactive.ThymeleafReactiveViewResolver;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebFluxConfigurer {



    private final ISpringWebFluxTemplateEngine templateEngine;


//    @Bean
//    @Description("Thymeleaf Template Engine")
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.setTemplateEngineMessageSource(messageSource());
//        return templateEngine;
//    }


//
//    @Bean
//    public SpringTemplateEngine templateEngine(){
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setEnableSpringELCompiler(true);
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.addDialect(new HelloDialect());
//        return templateEngine;
//    }
//    @Bean
//    public ThymeleafReactiveViewResolver thymeleafChunkedAndDataDrivenViewResolver() {
//        final ThymeleafReactiveViewResolver viewResolver = new ThymeleafReactiveViewResolver();
//        viewResolver.setTemplateEngine(templateEngine);
//        viewResolver.setOrder(1);
//        viewResolver.setResponseMaxChunkSizeBytes(8192); // OUTPUT BUFFER size limit
//        return viewResolver;
//    }
}
