package com.infotech.productapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfigurationSecurity {

//	@Bean
//	public InMemoryUserDetailsManager setUserPass() {
//		UserDetails details=User.withDefaultPasswordEncoder()
//				.username("Darshan")
//				.password("Darshan@12")
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(details);
//	}
//	
//	@Bean
//	public DefaultSecurityFilterChain frilterchain(HttpSecurity security) throws Exception {
//		
//		security.authorizeRequests((auther)->
//			auther.requestMatchers("/MyAdmin")
//			.authenticated())
//			.httpBasic();
//		
//		
//		return security.build();
//		
//	}
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(csrf -> csrf.disable()) 
	            .authorizeHttpRequests(auth -> auth
	                .requestMatchers("/**").permitAll() 
	                .anyRequest().authenticated()
	            );
	        return http.build();
	    }
}
