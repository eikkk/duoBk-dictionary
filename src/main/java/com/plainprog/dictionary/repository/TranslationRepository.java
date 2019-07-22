package com.plainprog.dictionary.repository;

import com.plainprog.dictionary.model.ItemTranslation;
import org.springframework.data.repository.CrudRepository;

public interface TranslationRepository extends CrudRepository<ItemTranslation,Integer> {
}
