package com.TVShows.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	  private final AuthenticationProvider authenticationProvider;

	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		  http
	        .authorizeHttpRequests().requestMatchers(
					"/home", "/page", "/shows", "/",
					"/auth", "/auth/**",  "/login", "/verification",
					"/success","/profile","/show/**", "", "/login?logout",
					"/pictures/**", "/css/**", "/js/**")
				  .permitAll()

				  .and().authorizeHttpRequests().requestMatchers(
						  "/show/{id}/comment",
						  "/user/{id}/decrement",
						  "/user/{id}/increment",
						  "/user/addShow",
						  "/user/image/upload")
				  .hasRole("USER")

				  .and().authorizeHttpRequests().requestMatchers(
						  "/tvshowform")
				  .hasRole("ADMINISTRATOR")
	        
	        .anyRequest()
	        .authenticated()
	        .and()
	        .authenticationProvider(authenticationProvider)
	        
	        .formLogin()
	        .loginPage("/auth").permitAll()
	        .loginProcessingUrl("/auth/login")
			.usernameParameter("email")
			.passwordParameter("password")
			.defaultSuccessUrl("/home", true)
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/home")
			.and()
			.exceptionHandling().accessDeniedPage("/error").and()
			.rememberMe().tokenValiditySeconds(2592000)
			.rememberMeParameter("checkRememberMe");
	    return http.build();
	  }
}
