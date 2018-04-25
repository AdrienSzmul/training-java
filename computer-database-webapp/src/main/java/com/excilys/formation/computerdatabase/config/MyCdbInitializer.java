package com.excilys.formation.computerdatabase.config;

import org.springframework.context.annotation.Import;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Import(PersistenceJPAConfig.class)
public class MyCdbInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { MyWebMvcConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { PersistenceJPAConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected DispatcherServlet createDispatcherServlet(
            WebApplicationContext servletAppContext) {
        final DispatcherServlet servlet = (DispatcherServlet) super.createDispatcherServlet(
                servletAppContext);
        servlet.setThrowExceptionIfNoHandlerFound(true);
        return servlet;
    }
}
