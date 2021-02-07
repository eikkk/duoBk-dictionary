package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    List<Item> findBySectionId(Integer sectionId);
    @Query(value = "select max(sort_index) from dict_item;",nativeQuery = true)
    public Integer getHighestIndex();
}
