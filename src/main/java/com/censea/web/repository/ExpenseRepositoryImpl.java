package com.censea.web.repository;

import com.censea.web.model.Expense;
import com.google.inject.Inject;

import java.util.List;

/**
 * Implementation of {@link ExpenseRepository}.
 */
public class ExpenseRepositoryImpl extends BaseRepositoryImpl<Expense>
    implements ExpenseRepository {

  @Inject
  ExpenseRepositoryImpl(OfyService ofyService) {
    super(ofyService);
  }

  @Override
  public List<Expense> findExpenseByUserId(String userId) {
    return ofyService.ofy().load().type(Expense.class).filter("userId", userId).list();
  }
}
