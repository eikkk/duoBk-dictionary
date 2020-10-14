package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.ItemTranslation;
import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findByAccessId(String accessId);
}