package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Integer> {
}
