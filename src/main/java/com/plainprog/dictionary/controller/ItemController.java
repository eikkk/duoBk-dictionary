package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.model.MoveItemModel;
import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/dictionary/item")
public class ItemController {
    @Autowired
    ItemService service;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createItem(@RequestBody Item item){
        service.create(item);
        return "SUCCESS";
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/move")
    public String move(@RequestBody MoveItemModel moveItemModel){
        service.moveItemToSection(moveItemModel);
        return "SUCCESS";
    }
}
