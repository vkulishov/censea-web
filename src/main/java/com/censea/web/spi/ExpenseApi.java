package com.censea.web.spi;

import com.censea.web.model.Expense;
import com.censea.web.repository.ExpenseRepository;
import com.censea.web.util.Constants;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.google.inject.Inject;

import java.util.List;
import java.util.logging.Logger;

@Api(
    name = "expense",
    version = "1",
    scopes = {Constants.EMAIL_SCOPE},
    clientIds = {Constants.WEB_CLiENT_ID,  Constants.API_EXPLORER_CLIENT_ID}
)
public class ExpenseApi {
  private static final Logger LOG = Logger.getLogger(ExpenseApi.class.getName());

  private final ExpenseRepository expenseRepository;

  @Inject
  ExpenseApi(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @ApiMethod(name = "save", httpMethod = "post")
  public void saveExpense(User user, Expense expense) throws UnauthorizedException {
    if (user == null) {
      throw new UnauthorizedException("Authorization required");
    }
    LOG.info(String.format("Save expense %s. User %s", expense, user));
    enrichExpense(user, expense);

    expenseRepository.save(expense);

    LOG.info(String.format("Expense %s has been successfully saved", expense));
  }

  @ApiMethod(name = "list", httpMethod = "get")
  public List<Expense> listUserExpenses(User user) throws UnauthorizedException {
    if (user == null) {
      throw new UnauthorizedException("Authorization required");
    }
    LOG.info(String.format("List expenses of user %s", user.getUserId()));

    return expenseRepository.findExpenseByUserId(user.getUserId());
  }

  private void enrichExpense(User user, Expense expense) {
    expense.setUserId(user.getUserId());
  }

}
