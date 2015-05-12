package com.censea.web.spi;

import com.censea.web.repository.ExpenseRepository;
import com.google.api.server.spi.guice.GuiceSystemServiceServletModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Guice module for configuring endpoints api.
 */
public class CenseaApiModule extends GuiceSystemServiceServletModule {
  @Override
  protected void configureServlets() {
    super.configureServlets();

    Set<Class<?>> serviceClasses = new HashSet<Class<?>>();
    serviceClasses.add(ExpenseApi.class);
    this.serveGuiceSystemServiceServlet("/_ah/spi/*", serviceClasses);
  }
}
