package com.censea.web.repository;

import com.censea.web.model.Expense;

import java.util.List;

/**
 * Repository for work with {@link Expense}.
 */
public interface ExpenseRepository extends BaseRepository<Expense> {
  List<Expense> findExpenseByUserId(String userId);
}
