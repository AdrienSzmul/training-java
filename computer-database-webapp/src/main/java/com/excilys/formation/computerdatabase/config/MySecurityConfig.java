package com.excilys.formation.computerdatabase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setKey("cdb");
        digestAuthenticationEntryPoint.setRealmName("computer-database");
        return digestAuthenticationEntryPoint;
    }

    @Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails user = User.withDefaultPasswordEncoder().username("user")
                .password("password").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }

    public DigestAuthenticationFilter digestAuthenticationFilter(
            DigestAuthenticationEntryPoint digestAuthenticationEntryPoint)
            throws Exception {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter
                .setAuthenticationEntryPoint(digestEntryPoint());
        digestAuthenticationFilter
                .setUserDetailsService(userDetailsServiceBean());
        return digestAuthenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().formLogin()
                .and()
                .addFilter(digestAuthenticationFilter(digestEntryPoint()));
    }
}
