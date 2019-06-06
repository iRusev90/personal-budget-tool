package bg.infa.pbt.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import bg.infa.pbt.security.SecurityRole;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN))
		.and()
			.authorizeRequests()
			.mvcMatchers("/index.html", "/js/**", "/node_modules/**", "/views/**", "api/bot**", "/favicon.ico", "api/user/**").permitAll()
			.mvcMatchers(HttpMethod.POST, "/api/message")
				.hasAnyRole(SecurityRole.ADMIN, SecurityRole.USER)
			
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginProcessingUrl("/api/login/**")
			.usernameParameter("name")
			.passwordParameter("password")
			.successHandler(this.authenticationSuccessHandler)
			.failureHandler(this.authenticationFailureHandler)
			.permitAll()
		.and()
			.logout()
			.logoutUrl("/api/logout/**")
			.logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.clearAuthentication(true)
			.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(inMemoryUserDetailsManager());
	}

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		final Properties users = new Properties();
		users.put("admin", (new BCryptPasswordEncoder()).encode("admin") + ",ROLE_" + SecurityRole.ADMIN + ",enabled");
		return new InMemoryUserDetailsManager(users);
	}

}