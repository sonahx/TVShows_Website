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
						  "/show/{id}/comment",
						  "/show/{showId}/comment/delete/{commentId}",
						  "/user/{id}/decrement",
						  "/user/{id}/increment",
						  "/user/addShow",
						  "/user/image/upload",
						  "/user/{show}/score/{score}"
						  )
				  .hasAnyRole("USER", "ADMINISTRATOR")

				  .and().authorizeHttpRequests().requestMatchers(
						   "/news/create")
				  .hasRole("ADMINISTRATOR")

				  .and().authorizeHttpRequests().requestMatchers(
					"/home", "/page", "/shows", "/", "/error",
					"/auth", "/auth/**",  "/login", "/verification", "/show/search/**","/news", "/news/**",
					"/success","/profile", "/show/**","/search/**","/search" ,"", "/login?logout",
					"/pictures/**", "/css/**", "/js/**", "/about", "/policy")
				  .permitAll()


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
