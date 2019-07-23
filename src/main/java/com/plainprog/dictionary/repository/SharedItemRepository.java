package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.SharedItem;
import org.springframework.data.repository.CrudRepository;

public interface SharedItemRepository extends CrudRepository<SharedItem,Integer> {
}
