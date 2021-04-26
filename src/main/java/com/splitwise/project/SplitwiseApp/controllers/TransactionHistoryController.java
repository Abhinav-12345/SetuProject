package com.splitwise.project.SplitwiseApp.controllers;

import com.splitwise.project.SplitwiseApp.models.TransactionHistory;
import com.splitwise.project.SplitwiseApp.repository.TransactionRespository;
import com.splitwise.project.SplitwiseApp.services.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionHistoryController {

    @Autowired
    private TransactionServiceImpl transactionService;

    @GetMapping("getHistory")
    public List<TransactionHistory> getTransaction(String groupName){

        return transactionService.getTheTransactions(groupName);
    }
}
