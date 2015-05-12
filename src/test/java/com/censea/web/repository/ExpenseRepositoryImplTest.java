package com.censea.web.repository;

import com.censea.web.model.Expense;
import com.censea.web.repository.fixture.ExpenseDataFixture;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.guiceberry.junit4.GuiceBerryRule;
import com.google.inject.Inject;
import com.googlecode.objectify.ObjectifyService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpenseRepositoryImplTest {
  private final LocalServiceTestHelper helper =
      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  @Rule
  public final GuiceBerryRule guiceBerry = new GuiceBerryRule(RepositoryTestEnv.class);

  private Closeable session;

  @Before
  public void setUp() {
    helper.setUp();
    session = ObjectifyService.begin();
  }

  @After
  public void tearDown() throws IOException {
    helper.tearDown();
    session.close();
  }

  @Inject
  private ExpenseRepository expenseRepository;

  @Inject
  private OfyService ofyService;

  @Test
  public void thatExpenseEntitySaved() throws Exception {
    // Prepare test data
    Expense expense = ExpenseDataFixture.createDefaultExpense();

    // Invoke tested method
    expenseRepository.save(expense);

    //Verify results
    Expense loadedExpense = ofyService.ofy().load().type(Expense.class).list().get(0);
    assertEquals(loadedExpense.getCategory(), expense.getCategory());
    assertEquals(loadedExpense.getDescription(), expense.getDescription());
    assertEquals(loadedExpense.getDate(), expense.getDate());
    assertEquals(loadedExpense.getSum(), expense.getSum());
    assertEquals(loadedExpense.getUserId(), expense.getUserId());
  }

  @Test
  public void thatExpensesFilteredByUserId() throws Exception {
    // Prepare test data
    Expense expense1User1 = ExpenseDataFixture.createExpenseWithUserId("user1");
    Expense expense2User1 = ExpenseDataFixture.createExpenseWithUserId("user1");
    Expense expenseUser2 = ExpenseDataFixture.createExpenseWithUserId("user2");

    ofyService.ofy().save().entities(expense1User1, expense2User1, expenseUser2).now();

    // Invoke tested method
    List<Expense> user1Expenses = expenseRepository.findExpenseByUserId("user1");
    List<Expense> user2Expenses = expenseRepository.findExpenseByUserId("user2");

    //Verify results
    assertEquals(2, user1Expenses.size());
    assertEquals(1, user2Expenses.size());
  }
}