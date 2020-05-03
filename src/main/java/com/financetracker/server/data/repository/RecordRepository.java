package com.financetracker.server.data.repository;

import com.financetracker.server.data.entity.Category;
import com.financetracker.server.data.entity.Record;
import com.financetracker.server.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecordRepository extends CrudRepository<Record,String> {

    public List<Record> findByUserAndCategory(User user, Category category);
    public List<Record> findById(long id);
}
