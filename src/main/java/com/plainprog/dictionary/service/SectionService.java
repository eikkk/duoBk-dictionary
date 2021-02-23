package com.plainprog.dictionary.service;

import com.plainprog.dictionary.Constants;
import com.plainprog.dictionary.helpers.SectionColorManager;
import com.plainprog.dictionary.model.ItemModel;
import com.plainprog.dictionary.model.SectionModel;
import com.plainprog.dictionary.model.TranslationModel;
import com.plainprog.dictionary.model.db.*;
import com.plainprog.dictionary.repository.ItemRepository;
import com.plainprog.dictionary.repository.SectionRepository;
import com.plainprog.dictionary.repository.SharedSectionRepository;
import com.plainprog.dictionary.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        section.setSortIndex(sectionRepository.getHighestIndex() + 1);
        if (section.getFake()==null)
            section.setFake(false);
        if (section.getColor() == null && !section.getFake()){
            List<Section> userSections = sectionRepository.findByDictId(section.getDictId());
            List<String> colors = userSections.stream().map(Section::getColor).collect(Collectors.toList());
            section.setColor(SectionColorManager.getMostSuitableColor(colors));
        }
        return sectionRepository.save(section);
    }
    public void fixNullColors(){
        List<Section> allSections = sectionRepository.findAll();
        for (Section section : allSections){
            if (section.getColor() == null){
                section.setColor(SectionColorManager.getMostSuitableColor(Collections.emptyList()));
                sectionRepository.save(section);
            }
        }
    }
    public Section createFakeSection(Integer dictId){
        Section section = new Section();
        section.setFake(true);
        section.setSortIndex(-1);
        section.setId(null);
        section.setPublic(false);
        section.setName("Fake"+dictId);
        section.setDictId(dictId);
        section.setColor("FFFFFF");
        return sectionRepository.save(section);
    }
    public Integer getFakeSectionId(Integer dictId){
        return  sectionRepository.getFakeSectionId(dictId);
    }
    public boolean createSharedSection(SharedSection sharedSection){
        Optional<Section> section = sectionRepository.findById(sharedSection.getSectionId());
        if(!section.isPresent())
            return false;
        sharedSection.setId(null);
        sharedSectionRepository.save(sharedSection);
        return true;
    }
    public boolean changeSectionName(Integer sectionId, String sectionName){
        Optional<Section> section = sectionRepository.findById(sectionId);
        if(!section.isPresent())
            return false;
        section.get().setName(sectionName);
        sectionRepository.save(section.get());
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
    public boolean modifySectionBatch(ArrayList<Section> sections){
        for (Section section : sections){
            if (!modifySection(section)) return  false;
        }
        return true;
    }
    public boolean modifySection(Section section){
        Optional<Section> sect = sectionRepository.findById(section.getId());
        if (sect.isPresent()){
            Section sectFromDb = sect.get();
            sectFromDb.setSortIndex(section.getSortIndex());
            sectFromDb.setName(section.getName());
            sectFromDb.setPublic(section.getPublic());
            sectionRepository.save(sectFromDb);
            return true;
        }
        return false;
    }
    public Section getById(Integer id){
       return sectionRepository.findById(id).orElse(null);
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
            itemModel.setOriginal(new TranslationModel(item.getValue(),item.getLang(),""));
            itemModel.setPublic(item.getPublic());
            itemModel.setSectionId(item.getSectionId());
            itemModel.setTranslations(new ArrayList<>(itemTranslations));
            itemModels.add(itemModel);
        }
        SectionModel sectionModel = new SectionModel();
        sectionModel.setId(sectionOptional.get().getId());
        sectionModel.setDictId(sectionOptional.get().getDictId());
        sectionModel.setName(sectionOptional.get().getName());
        sectionModel.setItems(itemModels);
        sectionModel.setColor(sectionOptional.get().getColor());
        return sectionModel;
    }
}
