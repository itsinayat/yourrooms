package com.inayat.yourrooms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inayat.yourrooms.security.handler.RestAccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	RestAccessDeniedHandler restAccessDeniedHandler;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthorizationFilter authorizationFilterBean() throws Exception {
		AuthorizationFilter authorizationFilterBean = new AuthorizationFilter();
		authorizationFilterBean.setAuthenticationManager(authenticationManagerBean());
		return authorizationFilterBean;
	}
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService);
	}
	  
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement()
	        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
		.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/user/login").permitAll()
		
			.antMatchers("/user/generate_otp").permitAll()
			.antMatchers("/user/mobile-login").permitAll()
            .antMatchers("/user/testAuth/**").hasRole("ADMIN")
            .antMatchers("/consumer").hasRole("CONSUMER")
            
            
            
            
            .antMatchers("/user/register/**").permitAll()
            .antMatchers("/user/generate-otp/**").permitAll()
            .antMatchers("/user/register-by-otp/**").permitAll()
            .antMatchers("/user/login-by-otp/**").permitAll()
            .antMatchers("/user/set-referral/**").permitAll()
        	.antMatchers("/user/logout").permitAll()
        	.antMatchers("/user/update-user/**").hasRole("ADMIN")
        	.antMatchers("/user/get-wallet/**").hasAnyRole("ADMIN","CONSUMER")
        	.antMatchers("/user/get-wallet-ransaction/**").hasAnyRole("ADMIN","CONSUMER")
        	.antMatchers("/user/get-booking-history**").hasAnyRole("ADMIN","CONSUMER")
        	.antMatchers("/user/get-all-coupons**").hasAnyRole("ADMIN","CONSUMER")
        	.antMatchers("/user/review-and-rating**").hasAnyRole("ADMIN","CONSUMER")
        	
        	
        	
        	
        	.antMatchers("/hotel/add-or-update-rooms-to-hotel/**").hasRole("ADMIN")
        	.antMatchers("/hotel/getAll-hotels/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/hotel/cancelBooking/**").hasAnyRole("ADMIN","CUSTOMER")
        	
        	.antMatchers("/hotel/add-rooms-to-hotel/**").hasRole("ADMIN")
        	.antMatchers("/hotel/getAll-rooms/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/hotel/getRoom/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/payment/getPaymentByOrderId/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/payment/getPaymentByOrderId/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/payment/payment/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/payment/create-refund/**").hasAnyRole("ADMIN","CUSTOMER")
        	.antMatchers("/payment/update-order/**").hasAnyRole("ADMIN","CUSTOMER")
        	
        	.antMatchers("/hotel_images/**").permitAll()
        	
        	
        	
        	
        	
            
            
			.anyRequest().authenticated().and()
			.exceptionHandling().accessDeniedHandler(restAccessDeniedHandler).authenticationEntryPoint(restAuthenticationEntryPoint);
    	
    	http
        	.addFilterBefore(authorizationFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }
}
