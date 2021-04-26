package com.splitwise.project.SplitwiseApp.services;


import com.splitwise.project.SplitwiseApp.controllers.ExpenseController;
import com.splitwise.project.SplitwiseApp.models.Expense;
import com.splitwise.project.SplitwiseApp.models.TransactionHistory;
import com.splitwise.project.SplitwiseApp.repository.TransactionRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Autowired
    private TransactionRespository transactionRespository;

    public void buildAndSaveTransaction(Expense paidExpense){

        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setAmount(paidExpense.getAmount());
        transactionHistory.setGroupName(paidExpense.getGroupName());
        transactionHistory.setPaidBy(paidExpense.getSplitWith());
        transactionHistory.setPaidTo(paidExpense.getPaidBy());


        saveTransaction(transactionHistory);

    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
    private void saveTransaction(TransactionHistory transactionHistory){
        transactionRespository.save(transactionHistory);

        LOGGER.info("Transaction done by user "+transactionHistory.getPaidBy()+" to user"+transactionHistory.getPaidTo());

    }


    public List<TransactionHistory> getTheTransactions(String groupName){
        return transactionRespository.findByGroupName(groupName);
    }
}
