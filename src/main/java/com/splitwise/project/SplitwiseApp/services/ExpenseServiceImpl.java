package com.splitwise.project.SplitwiseApp.services;

import com.splitwise.project.SplitwiseApp.dto.ExpenseDTO;
import com.splitwise.project.SplitwiseApp.models.Expense;
import com.splitwise.project.SplitwiseApp.models.GroupDB;
import com.splitwise.project.SplitwiseApp.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl{

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private ExpenseRepository expenseRepository;

    public void addExpense(ExpenseDTO expenseDTO, List<GroupDB> groupInfo, int groupSize){

        List<String> userName = groupInfo.stream().
                    map(groupDB -> groupDB.getUserName()).collect(Collectors.toList());

        List<Expense> expenseList = new ArrayList<>();

        Double amount = expenseDTO.getAmount();

        Double splitAmount = amount/groupSize;


       for(String user:userName){
           if(user.equals(expenseDTO.getPaidBy()))
               continue;
           buildExpenseList(expenseDTO.getPaidBy(),user, splitAmount, expenseDTO.getGroupName(), expenseList);
       }

       addThesplitAmount(expenseList, expenseDTO.getPaidBy());
    }

    public void buildExpenseList(String paidBy, String splitWith, Double splitAmount, String groupName, List<Expense> expenseList){
        Expense expense =new Expense();
        expense.setAmount(splitAmount);
        expense.setSplitWith(splitWith);
        expense.setPaidBy(paidBy);
        expense.setGroupName(groupName);

        expenseList.add(expense);

    }

    public List<Expense> getExpensesWithPositiveAmount(String groupName){
        List<Expense> expenses  = expenseRepository.findByGroupName(groupName);

        List<Expense> positiveExpense= expenses.stream().filter(expense -> expense.getAmount()>0).collect(Collectors.toList());

        return positiveExpense;
    }

    public String getTheOwesInfo(Expense expense){
        return expense.getSplitWith()+"  owes"+"  "+expense.getPaidBy()+" "+expense.getAmount();
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    private void addThesplitAmount(List<Expense> expenseList, String paidBy){
        expenseRepository.saveAll(expenseList);

        LOGGER.info("Expenses added for the expense by:", paidBy);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    public void updateExpenseToSettle(Expense payingExpense){

        expenseRepository.updateExpenseToSettle(payingExpense.getSplitWith(), payingExpense.getPaidBy());

        LOGGER.info(payingExpense.getSplitWith()+ " Has setted with " + payingExpense.getPaidBy());
    }
}
