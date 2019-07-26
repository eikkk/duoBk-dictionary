package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    List<Item> findBySectionId(Integer sectionId);
}
