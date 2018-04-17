package com.excilys.formation.computerdatabase.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyCdbInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    public MyCdbInitializer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { MyWebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
