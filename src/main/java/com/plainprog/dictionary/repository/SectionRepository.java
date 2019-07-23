package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.Section;
import org.springframework.data.repository.CrudRepository;

public interface SectionRepository extends CrudRepository<Section,Integer> {
}
