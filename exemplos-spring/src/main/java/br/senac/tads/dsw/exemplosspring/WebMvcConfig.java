/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 *
 * @author fedts
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configura as mensagens i18n para serem usadas com Bean Validation<br>
     * OBS: Ao usar configuração abaixo, a configuração do application.properties não é mais usada
     * 
     * Referência:
     * https://www.baeldung.com/spring-custom-validation-message-source
     */
    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("classpath:i18n/mensagens");
        source.setDefaultEncoding("UTF-8");
        source.setCacheSeconds(0);
        return source;
    }

    @Bean
    @Override
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    // Informação do locale passa a ser gerenciado em Cookie e válido para todas
    // as telas do sistema
    @Bean(name = "localeResolver")
    public CookieLocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(new Locale("pt", "BR"));
        return clr;
    }

     // Interceptador que permite trocar manualmente o idioma usado
    @Bean(name = "localeChangeInterceptor")
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload-acesso/**")
                .addResourceLocations("file:///C:/senac/tads/uploads/");
    }

}
