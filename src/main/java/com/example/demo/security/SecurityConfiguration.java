package com.example.demo.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static org.springframework.web.cors.CorsConfiguration.ALL;

import com.example.demo.auth.UserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	
	@Autowired
	UserDetailService detailsService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
	@Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

	@Autowired
    private LogoutSuccessHandler myLogoutSuccessHandler;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(detailsService);
		provider.setPasswordEncoder(passwordEncoder);
		
		return provider;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
        .authorizeRequests()
			.antMatchers(
                    	"/vendor/**",
                    	"/fonts/**",
                    	"/style/**",
                    	"/js/**",
						"/register/**",
						"/login/**",
						"/forget/**",
						"/market/**","/").permitAll()
        	.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.antMatchers("/owner/**").hasAuthority("ROLE_OWNER")
        	.anyRequest().authenticated()
        .and()
            .formLogin()
	            .loginPage("/login")
	            .defaultSuccessUrl("/index")
	            .failureUrl("/login?error=true")
	            .successHandler(myAuthenticationSuccessHandler)
	            .failureHandler(myAuthenticationFailureHandler)
                    .permitAll()
        .and()
            .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .permitAll() 
        .and()
			.csrf().disable();
        /*.and()
        	.rememberMe().rememberMeServices(rememberMeServices()).key("theKey");*/

		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui",
				"/configuration/security", "/webjars/**");
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.DELETE.name(),
				HttpMethod.PUT.name(), HttpMethod.HEAD.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name()));
		config.setAllowedHeaders(Collections.singletonList(ALL));
		config.setAllowedOrigins(Collections.singletonList(ALL));
		config.setMaxAge(1800L);
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	/*
	 * @Bean public RememberMeServices rememberMeServices() { return new
	 * CustomRememberMeServices("theKey", userDetailService, new
	 * InMemoryTokenRepositoryImpl()); }
	 */
}
