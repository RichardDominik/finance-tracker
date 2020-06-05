package com.financetracker.server.data.repository;

import com.financetracker.server.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findById(long id);
    List<User> findAll();
    Optional<User> findByEmail(String email);

    //TODO
    @Query(nativeQuery = true, value = "SELECT * FROM users")
    List<User> getStatistics();
}
