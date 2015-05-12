package com.censea.web.repository;

import com.censea.web.model.Expense;
import com.google.inject.AbstractModule;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.impl.translate.opt.BigDecimalLongTranslatorFactory;
import com.googlecode.objectify.impl.translate.opt.joda.JodaTimeTranslators;

/**
 * Guice module for configuring repository components.
 */
public class RepositoryModule extends AbstractModule {
  @Override
  protected void configure() {
    JodaTimeTranslators.add(ObjectifyService.factory());

    ObjectifyService.factory().register(Expense.class);

    bind(OfyService.class).asEagerSingleton();
    bind(ExpenseRepository.class).to(ExpenseRepositoryImpl.class);

  }
}
