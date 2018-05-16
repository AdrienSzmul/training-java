package com.excilys.formation.computerdatabase.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@Import(PersistenceJPAConfig.class)
@ComponentScan(basePackages = {
        "com.excilys.formation.computerdatabase.controllers",
        "com.excilys.formation.computerdatabase.config",
        "com.excilys.formation.computerdatabase.service" })
public class MyWebMvcConfig implements WebMvcConfigurer {
}
