package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.db.Dictionary;
import com.plainprog.dictionary.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryService {
    @Autowired
    DictionaryRepository repository;
    public Dictionary create(Dictionary dict){
        dict.setId(null);
        return repository.save(dict);
    }
}
