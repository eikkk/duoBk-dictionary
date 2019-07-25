package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.DictionaryModel;
import com.plainprog.dictionary.model.db.Dictionary;
import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.SharedDictionary;
import com.plainprog.dictionary.repository.DictionaryRepository;
import com.plainprog.dictionary.repository.SectionRepository;
import com.plainprog.dictionary.repository.SharedDictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DictionaryService {
    @Autowired
    DictionaryRepository dictRepository;
    @Autowired
    SharedDictRepository sharedDictRepository;
    @Autowired
    SectionRepository sectionRepository;


    public Dictionary createDict(Dictionary dict){
        dict.setId(null);
        return dictRepository.save(dict);
    }
    public boolean createSharedDict(SharedDictionary sharedDictionary){
        Optional<Dictionary> dictionary = dictRepository.findById(sharedDictionary.getDictId());
        if(!dictionary.isPresent())
            return false;
        sharedDictionary.setId(null);
        sharedDictRepository.save(sharedDictionary);
        return true;
    }
    public boolean deleteSharedDict(Integer sharedDictId){
        Optional<SharedDictionary> sharedDict = sharedDictRepository.findById(sharedDictId);
        if(!sharedDict.isPresent())
            return false;
        sharedDictRepository.delete(sharedDict.get());
        return true;
    }

    public boolean changePublicity(Integer dictId){
        Optional<Dictionary> dict = dictRepository.findById(dictId);
        if(!dict.isPresent())
            return false;
        dict.get().setPublic(!dict.get().getPublic());
        dictRepository.save(dict.get());
        return true;
    }

    public DictionaryModel getModel(Integer id){
        Optional<Dictionary> dictionary = dictRepository.findById(id);
        if(!dictionary.isPresent())
            return null;
        List<Section> sections = sectionRepository.findByDictId(id);
        DictionaryModel model = new DictionaryModel();
        model.setId(dictionary.get().getId());
        model.setOwnerId(dictionary.get().getUserId());
        model.setPublic(dictionary.get().getPublic());
        return null;
    }
}
