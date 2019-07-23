package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    SectionRepository repository;
    public Section create(Section section){
        section.setId(null);
        return repository.save(section);
    }
}
