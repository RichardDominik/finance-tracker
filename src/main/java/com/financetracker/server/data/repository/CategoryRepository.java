package com.financetracker.server.data.repository;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

    public List<Category> findByUser(User user);
    List<Category> findById(long id);
}
