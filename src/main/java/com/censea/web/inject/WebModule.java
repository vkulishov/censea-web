package com.censea.web.inject;

import com.google.inject.servlet.ServletModule;
import com.googlecode.objectify.ObjectifyFilter;

/**
 * Guice module for configuring web components.
 */
public class WebModule extends ServletModule {
  @Override
  protected void configureServlets() {
    bind(ObjectifyFilter.class).asEagerSingleton();
    filter("/*").through(ObjectifyFilter.class);
  }
}
