package TVShows.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import TVShows.domain.Role;
import TVShows.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	  private final JwtAuthenticationFilter jwtAuthFilter;
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
        .requestMatchers("/tvshowform").hasRole("USER")
	        
	        .anyRequest()
	        .authenticated()
	        .and()
	        .sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authenticationProvider(authenticationProvider)
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	        
	        .formLogin().loginPage("/auth").defaultSuccessUrl("/home")
			.usernameParameter("email")
			.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and()
			.exceptionHandling().accessDeniedPage("/403");
	    return http.build();

	  }
	
	
	

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//        .authorizeHttpRequests()
//        .requestMatchers("/home", "/", "/auth/**","/login", "/css/**", "/pictures/**")
//        .permitAll().and()
//             
//        .authorizeHttpRequests()
//        .requestMatchers("/show/**").permitAll().and()
//        
//        .authorizeHttpRequests()
//        .requestMatchers("/tvshowform").permitAll().and()
//   
//        
////        .authorizeHttpRequests()
////        .requestMatchers("/show").hasRole(Role.ROLE_ADMIN).anyRequest().authenticated().and()
//        
//        .formLogin().loginPage("/auth/login").defaultSuccessUrl("/home")
//		.usernameParameter("email")
//		.passwordParameter("password").and().logout().logoutSuccessUrl("/login?logout").and()
//		.exceptionHandling().accessDeniedPage("/403");
//    return http.build();
//	}

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder)
//    		throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//          .userDetailsService(userDetailsService)
//          .passwordEncoder(bCryptPasswordEncoder)
//          .and()
//          .build();
//    }


}
