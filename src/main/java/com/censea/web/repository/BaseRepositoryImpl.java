package com.censea.web.repository;

import com.google.common.base.Preconditions;

public abstract class BaseRepositoryImpl<T> implements BaseRepository<T> {
  protected final OfyService ofyService;

  BaseRepositoryImpl(OfyService ofyService) {
    this.ofyService = ofyService;
  }

  @Override
  public void save(T entity) {
    Preconditions.checkNotNull(entity);
    ofyService.ofy().save().entity(entity).now();
  }
}
