package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.MoveItemModel;
import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository repository;

    public Item create(Item item){
        item.setId(null);
        return  repository.save(item);
    }

    public void moveItemToSection(MoveItemModel moveItemModel){
        repository.moveToDict(Integer.toString(moveItemModel.getItemId()), Integer.toString(moveItemModel.getSectionId()));
    }
}
