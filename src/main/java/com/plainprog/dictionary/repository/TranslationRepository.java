package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.ItemTranslation;
import com.plainprog.dictionary.model.db.Section;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TranslationRepository extends CrudRepository<ItemTranslation,Integer> {
    List<ItemTranslation> findByItemId(Integer itemId);
}
