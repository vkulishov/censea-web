package com.censea.web.repository;

import com.google.guiceberry.GuiceBerryModule;
import com.google.inject.AbstractModule;

/**
 * Guice-berry environment for testing repositories.
 */
public class RepositoryTestEnv extends AbstractModule {
  @Override
  protected void configure() {
    install(new GuiceBerryModule());
    install(new RepositoryModule());
  }
}
