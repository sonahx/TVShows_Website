package TVShows.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
	        .csrf().disable()
	        .authorizeHttpRequests()
	        .requestMatchers("/home", "/","/auth", "/auth/**","/login", "/css/**", 
	        		"/pictures/**","/show/**", "/login?logout")
	        .permitAll()
	        
        .and().authorizeHttpRequests()
//        .requestMatchers("/tvshowform").hasRole("USER")
        .requestMatchers("/tvshowform").hasAuthority("USER")
	        
	        .anyRequest()
	        .authenticated()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        
	        .formLogin().loginPage("/auth").permitAll()
			.usernameParameter("email")
			.passwordParameter("password")
			.defaultSuccessUrl("/home").and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/home").and()
			.exceptionHandling().accessDeniedPage("/403");
	    return http.build();
	  }
}
