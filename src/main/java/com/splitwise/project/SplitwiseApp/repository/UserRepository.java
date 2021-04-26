package com.splitwise.project.SplitwiseApp.repository;

import com.splitwise.project.SplitwiseApp.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

    public Users findByUserName(String userName);
}
