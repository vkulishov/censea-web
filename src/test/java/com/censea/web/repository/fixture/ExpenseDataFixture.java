package com.censea.web.repository.fixture;

import com.censea.web.model.Expense;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class ExpenseDataFixture {
  public static final String DEFAULT_CATEGORY = "category_1";
  public static final String DEFAULT_USER_ID = "user";

  public static Expense createDefaultExpense() {
    Expense expense = new Expense();
    expense.setCategory(DEFAULT_CATEGORY);
    expense.setDate(new DateTime());
    expense.setDescription("description");
    expense.setSum(10L);
    expense.setUserId(DEFAULT_USER_ID);
    return expense;
  }
  public static Expense createExpenseWithUserId(String userId) {
    Expense expense = new Expense();
    expense.setCategory(DEFAULT_CATEGORY);
    expense.setDate(new DateTime());
    expense.setDescription("description");
    expense.setSum(10L);
    expense.setUserId(userId);
    return expense;
  }
}
