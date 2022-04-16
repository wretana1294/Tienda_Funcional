/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda;

import java.util.Locale;
import lombok.var;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


/**
 *
 * @author Carlitos
 * Internationalization --i18n-- 18 letras entre I y N
 * Locale: Representa es el lenguaje, la región geográfica, variantes
 * del dialecto/idioma, de un usuario
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    
    @Bean
    public SessionLocaleResolver localeResolver(){
        var sir = new SessionLocaleResolver();
        sir.setDefaultLocale(new Locale("en"));    
        return sir;
    }
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registro){
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro){
        registro.addViewController("/").setViewName("personas");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
                
    }
}
