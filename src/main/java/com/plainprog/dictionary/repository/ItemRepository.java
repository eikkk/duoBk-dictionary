package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Integer> { }
