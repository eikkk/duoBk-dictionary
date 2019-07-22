package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.SharedDictionary;
import org.springframework.data.repository.CrudRepository;

public interface SharedDictRepository extends CrudRepository<SharedDictionary,Integer> {
}
