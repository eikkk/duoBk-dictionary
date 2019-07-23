package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Item;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ItemRepository extends CrudRepository<Item,Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE dict_item SET dict_item.section_id=:sectionId WHERE dict_item.id = :itemId ",nativeQuery = true)
    public void moveToDict(@Param(value = "itemId") String itemId, @Param(value = "sectionId") String sectionId);
}
