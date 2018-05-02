package com.excilys.formation.computerdatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    // @Autowired
    // private UserDetailsService userDetailsService;
    //
    // @Override
    // public void configure(WebSecurity web) throws Exception {
    // web.ignoring().antMatchers("/static/**");
    // }
    //
    // @Override
    // @Bean
    // public UserDetailsService userDetailsServiceBean() throws Exception {
    // return userDetailsService;
    // }
    //
    // @Bean
    // public DigestAuthenticationEntryPoint digestEntryPoint() {
    // DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new
    // DigestAuthenticationEntryPoint();
    // digestAuthenticationEntryPoint.setKey("cdb");
    // digestAuthenticationEntryPoint.setRealmName("computer-database");
    // return digestAuthenticationEntryPoint;
    // }
    //
    // public DigestAuthenticationFilter digestAuthenticationFilter(
    // DigestAuthenticationEntryPoint digestAuthenticationEntryPoint)
    // throws Exception {
    // DigestAuthenticationFilter digestAuthenticationFilter = new
    // DigestAuthenticationFilter();
    // digestAuthenticationFilter
    // .setAuthenticationEntryPoint(digestEntryPoint());
    // digestAuthenticationFilter
    // .setUserDetailsService(userDetailsServiceBean());
    // return digestAuthenticationFilter;
    // }
    //
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.authorizeRequests().anyRequest().authenticated().and().formLogin()
    // .and()
    // .addFilter(digestAuthenticationFilter(digestEntryPoint()));
    // }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("adrien").password("123adr")
                .authorities("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/securityNone").permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}
