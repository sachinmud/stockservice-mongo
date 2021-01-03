package com.sachin.tradeservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sachin.tradeservice.service.impl.UserServiceProxy;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class ApplicationSecurityConfigBasicAuth extends WebSecurityConfigurerAdapter {
	
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    UserServiceProxy userService;

    @Autowired
    public ApplicationSecurityConfigBasicAuth(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.authenticationProvider(authenticationProvider());
    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;

    }    
    

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()  // CSRF disabled, useful for form based
		.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/index").permitAll() //Allow index without authentication
//		.antMatchers("/v1/user/**", "/v1/role/**").hasAnyRole(ADMIN.name(), CLIENT.name())
		.anyRequest().authenticated()	//all other request needs to be authenticated
		.and()
//		.userDetailsService(userService)
		.httpBasic();
//		.and()
//		.sessionManagement()
//	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
/*	
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
    	
 	
        UserDetails adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name()) // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails businessUser = User.builder()
                .username("user1")
                .password(passwordEncoder.encode("password"))
//                .roles(CLIENT.name()) // ROLE_CLIENT
                .authorities(CLIENT.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
        		adminUser,
        		businessUser
        );

    }    
*/
}
