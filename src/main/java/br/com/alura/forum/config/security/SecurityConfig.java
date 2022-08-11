package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.forum.repository.UserRepository;

@EnableWebSecurity
@Configuration
@Profile(value = {"prod","test"})
public class SecurityConfig {
	
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Para recuperar o usuario autenticado de Usuário
	@Bean
	public UserDetailsService userDetailsService() {
		return new AutenticacaoService();
	}
	
	//Bean criado para ser usado dentro do Provider
	@Bean 
	public AutenticacaoService authService() {
		return new AutenticacaoService();
	}
	
	//Implementação de Provider de acordo com a estrutura da minha api
	@Bean
	public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.antMatchers(HttpMethod.GET, "/topicos").permitAll()
				.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
					.antMatchers(HttpMethod.POST, "/auth").permitAll()
						.antMatchers(HttpMethod.GET,"/actuator/**").permitAll()
							.antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("MOD")
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
			
		
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer web() {
	    return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**","/configuration/**", "/swagger-resources/**");

	}
	
}
