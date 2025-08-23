package indeedcoder.springsecurityjwtdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import indeedcoder.springsecurityjwtdemo.jwt.JwtAccessDeniedHandler;
import indeedcoder.springsecurityjwtdemo.jwt.JwtAuthenticationEntryPoint;
import indeedcoder.springsecurityjwtdemo.jwt.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter filter, JwtAuthenticationEntryPoint authEntryPoint, JwtAccessDeniedHandler accessDeniedHandler) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/home/**", "/about/**").permitAll()
				.requestMatchers("/login").permitAll()
				.requestMatchers("/admin-annotation").hasRole("ADMIN")
				.requestMatchers("/admin-context").hasRole("ADMIN")
				.requestMatchers("/order/**").permitAll().anyRequest().authenticated() // /person/** is authenticated
		).httpBasic(Customizer.withDefaults())
		.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.exceptionHandling(e -> e.authenticationEntryPoint(authEntryPoint).accessDeniedHandler(accessDeniedHandler))
		.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
		.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
