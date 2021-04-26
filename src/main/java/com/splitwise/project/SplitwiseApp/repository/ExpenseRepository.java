package com.splitwise.project.SplitwiseApp.repository;

import com.splitwise.project.SplitwiseApp.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {


    public List<Expense> findByGroupName(String groupName);

    public List<Expense> findBySplitWith(String splitWith);



    @Modifying
    @Transactional
    @Query("UPDATE  Expense e SET e.amount= 0 where e.splitWith = ?1 AND e.paidBy = ?2")
    public void updateExpenseToSettle(String splitWith, String paidTo);

}
