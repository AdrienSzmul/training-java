package com.excilys.formation.computerdatabase.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.excilys.formation.computerdatabase.config",
        "com.excilys.formation.computerdatabase.ui",
        "com.excilys.formation.computerdatabase.service",
        "com.excilys.formation.computerdatabase.paginator" })
@Import(PersistenceJPAConfig.class)
public class MyCliConfig {
}
