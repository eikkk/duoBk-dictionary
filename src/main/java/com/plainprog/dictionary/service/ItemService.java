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

    public ItemModel createItem(ItemWithTranslationsModel item){
        item.getItem().setId(null);
        Integer highestIndex = itemRepository.getHighestIndex();
        Item itemToSave = item.getItem();
        itemToSave.setSortIndex(highestIndex + 1);
        Item itemSaved = itemRepository.save(itemToSave);
        if (item.getTranslations() == null)
            item.setTranslations(new ArrayList<>());
        for(ItemTranslation translation : item.getTranslations()){
            translation.setId(null);
            translation.setItemId(itemSaved.getId());
            translationRepository.save(translation);
        }
        return  getModel(itemSaved.getId());
    }
    public List<Item> getItemsBySectionId(Integer sectionId){
        return itemRepository.findBySectionId(sectionId);
    }
    public ItemModel edit(ItemWithTranslationsModel item){
        Optional<Item> itemFromDb = itemRepository.findById(item.getItem().getId());
        if(!itemFromDb.isPresent())
            return null;
        Item itemSaved = itemRepository.save(item.getItem());
        List<ItemTranslation> itemTranslations = translationRepository.findByItemId(itemFromDb.get().getId());
        for(ItemTranslation translation : itemTranslations){
            boolean stillExist = false;
            for (ItemTranslation translationEdited : item.getTranslations()){
                if (translation.getId().equals(translationEdited.getId())){
                    translationEdited.setItemId(item.getItem().getId());
                    translationRepository.save(translationEdited);
                    stillExist = true;
                }
            }
            if (!stillExist){
                translationRepository.delete(translation);
            }
        }
        for (ItemTranslation translationEdited : item.getTranslations()) {
            if (translationEdited.getId() == null) {
                translationEdited.setItemId(item.getItem().getId());
                translationRepository.save(translationEdited);
            }
        }
        return getModel(itemSaved.getId());
    }
    public boolean modifyItemBatch(ArrayList<Item> items){
        for (Item item : items){
            if (!modifyItem(item)) return  false;
        }
        return true;
    }
    public boolean modifyItem(Item item){
        Optional<Item> itemOptional = itemRepository.findById(item.getId());
        if (itemOptional.isPresent()){
            Item itemFromDb = itemOptional.get();
            itemFromDb.setLang(item.getLang());
            itemFromDb.setPublic(item.getPublic());
            itemFromDb.setSortIndex(item.getSortIndex());
            itemFromDb.setSectionId(item.getSectionId());
            itemFromDb.setValue(item.getValue());
            itemRepository.save(itemFromDb);
            return true;
        }
        return false;
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
        itemModel.setOriginal(new TranslationModel(itemOptional.get().getValue(),itemOptional.get().getLang(),""));
        itemModel.setPublic(itemOptional.get().getPublic());
        itemModel.setSectionId(itemOptional.get().getSectionId());
        itemModel.setSortIndex(itemOptional.get().getSortIndex());
        itemModel.setTranslations(new ArrayList<>(itemTranslations));
        return itemModel;
    }
}
