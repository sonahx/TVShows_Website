package TVShows.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	  private final AuthenticationProvider authenticationProvider;

	  @Bean
	  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests()
	        .requestMatchers("/home", "/","/auth", "/auth/**","/login", "/css/**", 
	        		"/pictures/**","/show/**", "/login?logout")
	        .permitAll()
	        
        .and().authorizeHttpRequests()
        .requestMatchers("/profile").hasRole("USER")
	        
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
