package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.DictionaryModel;
import com.plainprog.dictionary.model.ItemModel;
import com.plainprog.dictionary.model.SectionModel;
import com.plainprog.dictionary.model.TranslationModel;
import com.plainprog.dictionary.model.db.*;
import com.plainprog.dictionary.repository.*;
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
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    TranslationRepository translationRepository;


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
    public DictionaryModel getDictByAccessToken(String accessId){
        Integer id = dictRepository.getDictIdByAccessId(accessId);
        return  getModel(id);
    }
    public DictionaryModel getModel(Integer id){
        Optional<Dictionary> dictionary = dictRepository.findById(id);
        if(!dictionary.isPresent())
            return null;
        List<Section> sections = sectionRepository.findByDictId(id);
        ArrayList<SectionModel> sectionModels = new ArrayList<>();
        for(Section section : sections){
            ArrayList<ItemModel> itemModels = new ArrayList<>();
            List<Item> items = itemRepository.findBySectionId(section.getId());
            for(Item item : items){
                ArrayList<TranslationModel> translationModels = new ArrayList<>();
                List<ItemTranslation> itemTranslations = translationRepository.findByItemId(item.getId());
                for(ItemTranslation translation : itemTranslations){
                    translationModels.add(new TranslationModel(translation));
                }
                ItemModel itemModel = new ItemModel();
                itemModel.setId(item.getId());
                itemModel.setOriginal(new TranslationModel(item.getValue(),item.getLang()));
                itemModel.setPublic(item.getPublic());
                itemModel.setSectionId(item.getSectionId());
                itemModel.setTranslations(translationModels);
                itemModels.add(itemModel);
            }
            SectionModel sectionModel = new SectionModel();
            sectionModel.setId(section.getId());
            sectionModel.setDictId(section.getDictId());
            sectionModel.setName(section.getName());
            sectionModel.setItems(itemModels);
            sectionModels.add(sectionModel);
        }
        DictionaryModel model = new DictionaryModel();
        model.setId(dictionary.get().getId());
        model.setOwnerId(dictionary.get().getUserId());
        model.setPublic(dictionary.get().getPublic());
        model.setSections(sectionModels);
        return model;
    }
}
