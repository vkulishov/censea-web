package com.censea.web;

import com.censea.web.inject.WebModule;
import com.censea.web.repository.RepositoryModule;
import com.censea.web.spi.CenseaApiModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class CenseaServletContextListener extends GuiceServletContextListener {
  @Override
  protected Injector getInjector() {
    return Guice.createInjector(new RepositoryModule(), new WebModule(), new CenseaApiModule());
  }
}
