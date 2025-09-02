package project.com.cognifyz.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsService()
	{
		UserDetails admin =User
				
		        .withUsername("admin")
		        .password(passwordEncoder().encode("admin123"))
		        .roles("ADMIN")
		        .build();

		return new InMemoryUserDetailsManager(admin);
	}
	
	    @Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		    http
		        .csrf().disable()
		        .authorizeHttpRequests(auth -> auth
		            .requestMatchers("/admin/login","/Home.html", "/student-form.html", "/success.html", "/api/users/save", "/css/**", "/js/**")
		            .permitAll()
		            .requestMatchers("/view.html", "/api/users/**").authenticated()
		        )
		        .formLogin(form -> form
		            .loginPage("/admin-login.html")
		            .loginProcessingUrl("/admin/login")
		            .defaultSuccessUrl("/view.html", true)
		            .permitAll()
		        )
		        .logout(logout -> logout
		            .logoutSuccessUrl("/Home.html")
		            .invalidateHttpSession(true)
		            .clearAuthentication(true)
		            .permitAll()
		        );
       
            
            

	    return http.build();
	}


}
