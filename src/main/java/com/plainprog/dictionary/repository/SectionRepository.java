package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.model.db.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section,Integer> {
    List<Section> findByDictId(Integer dictId);
    List<Section> findAll();
    @Query(value = "select max(sort_index) from dict_section;",nativeQuery = true)
    public Integer getHighestIndex();
    @Query(value = "select id from dict_section where dict_id=:dictId && fake=1;",nativeQuery = true)
    public Integer getFakeSectionId(Integer dictId);
}
