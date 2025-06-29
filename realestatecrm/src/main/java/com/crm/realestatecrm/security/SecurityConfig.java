package com.crm.realestatecrm.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private CustomeSuccessHandler customeSuccessHandler;
	
	@Bean
	public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        jdbcUserDetailsManager.setUsersByUsernameQuery(
            "SELECT username, password, enabled FROM users WHERE username=?"
        );

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT username, authority FROM authorities WHERE username=?"
        );

        return jdbcUserDetailsManager;
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests(configurer -> configurer
				.requestMatchers("/", "/signup", "/css/**", "/js/**").permitAll()
				.requestMatchers("/admin", "/updateStatus", "/updateSalesStatus", "/dashboard/searchManagers").hasRole("ADMIN")
				.anyRequest().authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.loginProcessingUrl("/loginuser")
						.successHandler(customeSuccessHandler)
						.permitAll()
				)
				.headers(headers -> headers
		                .cacheControl(cache -> cache.disable())
		         )
				.logout(logout -> logout.permitAll())
				.exceptionHandling(configure -> configure.accessDeniedPage("/access-denied"));
		
		return http.build();
	}
}
