package com.financetracker.server.data.repository;

import com.financetracker.server.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {


    List<User> findAll();
    List<User> findByUid(String uid);
}
