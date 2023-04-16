package evgen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf().disable()
				.authorizeHttpRequests(
						authorizeConfig ->{
							authorizeConfig.requestMatchers("/", "/error", "/falcon.ico", "/users/**").permitAll();
							authorizeConfig.anyRequest().authenticated();
						})
				.formLogin(Customizer.withDefaults())
				.oauth2Login(Customizer.withDefaults())
				.build();

	}
}
