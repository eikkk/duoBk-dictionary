package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SectionRepository extends CrudRepository<Section,Integer> {
    List<Section> findByDictId(Integer dictId);
    @Query(value = "select max(sort_index) from dict_section;",nativeQuery = true)
    public Integer getHighestIndex();
}
