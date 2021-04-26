package com.splitwise.project.SplitwiseApp.repository;

import com.splitwise.project.SplitwiseApp.models.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRespository extends JpaRepository<TransactionHistory, String> {


    public List<TransactionHistory> findByGroupName(String groupName);

}
