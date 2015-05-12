package com.censea.web.model;

import com.google.common.base.MoreObjects;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@Entity
public class Expense {
  @Id
  private Long id;
  @Index
  private DateTime date;
  private long sum;
  private String description;
  @Index
  private String category;
  @Index
  private String userId;

  public Long getId() {
    return id;
  }

  public DateTime getDate() {
    return date;
  }

  public void setDate(DateTime date) {
    this.date = date;
  }

  public long getSum() {
    return sum;
  }

  public void setSum(long sum) {
    this.sum = sum;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("date", date)
        .add("sum", sum)
        .add("description", description)
        .add("category", category)
        .add("userId", userId)
        .toString();
  }
}
