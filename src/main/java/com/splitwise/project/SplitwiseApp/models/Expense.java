package com.splitwise.project.SplitwiseApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long expenseId;

    private String groupName;

    private String paidBy;

    public Expense() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    private Double amount;

    private  String splitWith;

    public Expense(Long expenseId, String paidBy, Double amount, String splitWith) {
        this.expenseId = expenseId;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splitWith = splitWith;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSplitWith() {
        return splitWith;
    }

    public void setSplitWith(String splitWith) {
        this.splitWith = splitWith;
    }
}
