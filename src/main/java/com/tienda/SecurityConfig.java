 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda;

import com.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author Carlitos
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userDetailsService);

        return daoAuthenticationProvider;
    }

    public SecurityConfig(UserService userPrincipalDetailsService) {
        this.userDetailsService = userPrincipalDetailsService;
    }
    
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception{
////          auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
////        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
////        
//                auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("123") /*-->omite encripcion y solo toma en cuenta el 123*/
//                .roles("ADMIN", "VENDEDOR", "USER")
//                .and()
//                .withUser("vendedor")
//                .password("{noop}123") /*-->omite encripcion y solo toma en cuenta el 123*/
//                .roles("VENDEDOR", "USER")  
//                .and()
//                .withUser("user")
//                .password("{noop}123") /*-->omite encripcion y solo toma en cuenta el 123*/
//                .roles("USER");                      
//    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(authenticationProvider());
    }
    
    //metodo para realizar autorización de accesos
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/nuevaPersona","/personas","/modificarPersona/{getId}","/") //--> se mapean todos los url a los que el rol puede acceder
                .hasRole("ADMIN")
                .antMatchers("/nuevaPersona","/personas", "/modificarPersona/{getId}","/")
                .hasAnyRole("ADMIN", "VENDEDOR")
                .antMatchers("/nuevaPersona","/personas","/modificarPersona/{getId}","/")
                .hasAnyRole("USER", "VENDEDOR", "ADMIN")
                .and()
                .formLogin()
//                .loginPage("/")
                .loginProcessingUrl("/signin").permitAll();
    }           
}
