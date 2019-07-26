package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.*;
import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.model.db.ItemTranslation;
import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.SharedItem;
import com.plainprog.dictionary.repository.ItemRepository;
import com.plainprog.dictionary.repository.SectionRepository;
import com.plainprog.dictionary.repository.SharedItemRepository;
import com.plainprog.dictionary.repository.TranslationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    SharedItemRepository sharedItemRepository;
    @Autowired
    TranslationRepository translationRepository;
    @Autowired
    SectionRepository sectionRepository;

    public Item createItem(ItemWithTranslationsModel item){
        item.getItem().setId(null);
        Item itemSaved = itemRepository.save(item.getItem());
        for(ItemTranslation translation : item.getTranslations()){
            translation.setId(null);
            translation.setItemId(itemSaved.getId());
            translationRepository.save(translation);
        }
        return  itemSaved;
    }

    public boolean edit(ItemWithTranslationsModel item){
        Optional<Item> itemFromDb = itemRepository.findById(item.getItem().getId());
        if(!itemFromDb.isPresent())
            return false;
        itemRepository.save(item.getItem());
        for(ItemTranslation translation : item.getTranslations()){
            translation.setItemId(item.getItem().getId());
            translationRepository.save(translation);
        }
        return true;
    }

    public boolean moveItemToSection(MoveItemModel moveItemModel){
        Optional<Section> section = sectionRepository.findById(moveItemModel.getSectionId());
        if(!section.isPresent())
            return false;
        Optional<Item> item = itemRepository.findById(moveItemModel.getItemId());
        if(!item.isPresent())
            return false;
        item.get().setSectionId(moveItemModel.getSectionId());
        itemRepository.save(item.get());
        return true;
    }

    public boolean createSharedItem(SharedItem sharedItem){
        Optional<Item> item = itemRepository.findById(sharedItem.getItemId());
        if(!item.isPresent())
            return false;
        sharedItem.setId(null);
        sharedItemRepository.save(sharedItem);
        return true;
    }
    public boolean deleteTranslation(Integer translationId){
        Optional<ItemTranslation> translation = translationRepository.findById(translationId);
        if(!translation.isPresent())
            return false;
        translationRepository.delete(translation.get());
        return true;
    }
    public boolean deleteItem(Integer itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        if(!item.isPresent())
            return false;
        itemRepository.delete(item.get());
        return true;
    }
    public boolean deleteSharedItem(Integer sharedItemId){
        Optional<SharedItem> sharedItem = sharedItemRepository.findById(sharedItemId);
        if(!sharedItem.isPresent())
            return false;
        sharedItemRepository.delete(sharedItem.get());
        return true;
    }
    public boolean changePublicity(Integer itemId){
        Optional<Item> item = itemRepository.findById(itemId);
        if(!item.isPresent())
            return false;
        item.get().setPublic(!item.get().getPublic());
        itemRepository.save(item.get());
        return true;
    }

    public ItemModel getModel(Integer id){
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(!itemOptional.isPresent())
            return null;
        ArrayList<TranslationModel> translationModels = new ArrayList<>();
        List<ItemTranslation> itemTranslations = translationRepository.findByItemId(itemOptional.get().getId());
        for(ItemTranslation translation : itemTranslations){
            translationModels.add(new TranslationModel(translation));
        }
        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemOptional.get().getId());
        itemModel.setOriginal(new TranslationModel(itemOptional.get().getValue(),itemOptional.get().getLang()));
        itemModel.setPublic(itemOptional.get().getPublic());
        itemModel.setSectionId(itemOptional.get().getSectionId());
        itemModel.setTranslations(translationModels);
        return itemModel;
    }
}
