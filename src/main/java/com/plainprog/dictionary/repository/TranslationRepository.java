package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.db.ItemTranslation;
import org.springframework.data.repository.CrudRepository;

public interface TranslationRepository extends CrudRepository<ItemTranslation,Integer> {
}
