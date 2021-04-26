package com.splitwise.project.SplitwiseApp.repository;


import com.splitwise.project.SplitwiseApp.models.GroupDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupDB,String> {

    public List<GroupDB> findByGroupName(String groupName);
}
