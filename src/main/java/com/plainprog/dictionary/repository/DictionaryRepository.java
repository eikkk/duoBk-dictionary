package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Dictionary;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<Dictionary,Integer> {
}
