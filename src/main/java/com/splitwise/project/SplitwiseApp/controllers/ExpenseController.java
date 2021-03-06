package com.splitwise.project.SplitwiseApp.controllers;


import com.splitwise.project.SplitwiseApp.dto.ExpenseDTO;
import com.splitwise.project.SplitwiseApp.dto.SettleDTO;
import com.splitwise.project.SplitwiseApp.models.Expense;
import com.splitwise.project.SplitwiseApp.models.GroupDB;
import com.splitwise.project.SplitwiseApp.repository.ExpenseRepository;
import com.splitwise.project.SplitwiseApp.services.ExpenseServiceImpl;
import com.splitwise.project.SplitwiseApp.services.GroupNameService;
import com.splitwise.project.SplitwiseApp.services.TransactionServiceImpl;
import com.splitwise.project.SplitwiseApp.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private GroupNameService groupNameService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ExpenseServiceImpl expenseService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TransactionServiceImpl transactionService;

    @PostMapping("/addExpense")
    public String addExpense( @RequestBody ExpenseDTO expenseDTO){
        String groupName = expenseDTO.getGroupName();

        if(!groupNameService.isGroupExists(groupName)){
            return "Enter a valid Group";
        }

        if(!userService.isUserAccountExists(expenseDTO.getPaidBy())){
            return "Enter the valid paid by user name";
        }


        List<GroupDB> groupInfo = groupNameService.getAllUsersOfAGroup(groupName);

        expenseService.addExpense(expenseDTO, groupInfo, groupInfo.size());

        return "Expense Added Succesfully by User:  "+expenseDTO.getPaidBy();
    }


    @GetMapping("/getOweList/{groupName}")
    public List<String> getTheOweList(String groupName){

        List<String> owesList = new ArrayList<>();
        if(!groupNameService.isGroupExists(groupName)){
            owesList.add("Enter valid groupName");
            return owesList;
        }

        List<Expense> expenses = expenseService.getExpensesWithPositiveAmount(groupName);

        for(Expense expense:expenses){
            String owe = expenseService.getTheOwesInfo(expense);
            owesList.add(owe);
        }

        return owesList;
    }


    @PostMapping("/settlePayment")
    public String payTheAmount(@RequestBody SettleDTO settleDTO){


        if(!userService.isUserAccountExists(settleDTO.getPaidBy()) || !userService.isUserAccountExists(settleDTO.getPaidTo())){
            return "Any of the user "+settleDTO.getPaidBy()+" or "+settleDTO.getPaidTo()+ " doesnot exists";
        }

        List<Expense> payingExpense = expenseRepository.findBySplitWith(settleDTO.getPaidBy());

        Expense expenseToPay=null;

        for(Expense expense:payingExpense){
            if(expense.getPaidBy().equals(settleDTO.getPaidTo())){
                expenseToPay= expense;
                break;
            }
        }
        /*Suppose if a user enter to pay from the user who doesnot owe any amount*/
        if(expenseToPay==null){
            return "Entered user"+expenseToPay.getPaidBy() +" doesnot owe any amount with others";
        }
        Expense transaction = expenseToPay;

        Double leftAmount = Math.abs(expenseToPay.getAmount() - settleDTO.getAmount());

        if(leftAmount>=1){
            return "Please enter the full amount to pay"+" "+expenseToPay.getAmount();
        }

        expenseService.updateExpenseToSettle(expenseToPay);



        transactionService.buildAndSaveTransaction(expenseToPay);

        return expenseToPay.getSplitWith()+ " Has settled with " + expenseToPay.getPaidBy();

    }

}
