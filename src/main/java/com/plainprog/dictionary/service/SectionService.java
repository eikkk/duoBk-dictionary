package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.SharedSection;
import com.plainprog.dictionary.repository.SectionRepository;
import com.plainprog.dictionary.repository.SharedSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {
    @Autowired
    SectionRepository sectionRepository;
    @Autowired
    SharedSectionRepository sharedSectionRepository;

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
}
