package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.DictionaryModel;
import com.plainprog.dictionary.model.ItemModel;
import com.plainprog.dictionary.model.SectionModel;
import com.plainprog.dictionary.model.TranslationModel;
import com.plainprog.dictionary.model.db.*;
import com.plainprog.dictionary.repository.ItemRepository;
import com.plainprog.dictionary.repository.SectionRepository;
import com.plainprog.dictionary.repository.SharedSectionRepository;
import com.plainprog.dictionary.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    SharedSectionRepository sharedSectionRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    TranslationRepository translationRepository;

    public Section createSection(Section section){
        section.setId(null);
        return sectionRepository.save(section);
    }

    public boolean createSharedSection(SharedSection sharedSection){
        Optional<Section> section = sectionRepository.findById(sharedSection.getSectionId());
        if(!section.isPresent())
            return false;
        sharedSection.setId(null);
        sharedSectionRepository.save(sharedSection);
        return true;
    }
    public boolean deleteSection(Integer sectionId){
        Optional<Section> section = sectionRepository.findById(sectionId);
        if(!section.isPresent())
            return false;
        sectionRepository.delete(section.get());
        return true;
    }
    public boolean deleteSharedSection(Integer sharedSectionId){
        Optional<SharedSection> sharedSection = sharedSectionRepository.findById(sharedSectionId);
        if(!sharedSection.isPresent())
            return false;
        sharedSectionRepository.delete(sharedSection.get());
        return true;
    }
    public boolean changePublicity(Integer sectionId){
        Optional<Section> section = sectionRepository.findById(sectionId);
        if(!section.isPresent())
            return false;
        section.get().setPublic(!section.get().getPublic());
        sectionRepository.save(section.get());
        return true;
    }
    public SectionModel getModel(Integer id){
        Optional<Section> sectionOptional = sectionRepository.findById(id);
        if(!sectionOptional.isPresent())
            return null;
        ArrayList<ItemModel> itemModels = new ArrayList<>();
        List<Item> items = itemRepository.findBySectionId(sectionOptional.get().getId());
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
        sectionModel.setId(sectionOptional.get().getId());
        sectionModel.setDictId(sectionOptional.get().getDictId());
        sectionModel.setName(sectionOptional.get().getName());
        sectionModel.setItems(itemModels);
        return sectionModel;
    }
}
