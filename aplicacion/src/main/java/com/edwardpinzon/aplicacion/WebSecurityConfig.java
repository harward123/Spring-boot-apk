package com.edwardpinzon.aplicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edwardpinzon.aplicacion.service.UserDetailsServiceImpl;

@Configurable
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
    };
	
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
        .authorizeRequests()
        .antMatchers(resources).permitAll()  
        .antMatchers("/","/index").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .defaultSuccessUrl("/userForm")
            .failureUrl("/login?error=true")
            .usernameParameter("username")
            .passwordParameter("password")
            .and()
            .csrf().disable()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/login?logout");
    }
	
	 BCryptPasswordEncoder bCryptPasswordEncoder;

	    @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
			bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	        return bCryptPasswordEncoder;
	    }
	    
	    @Autowired
	    UserDetailsServiceImpl userDetailsService;
	    
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
	    	//Especificar el encargado del login y encriptacion del password
	        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	    }
	
}
