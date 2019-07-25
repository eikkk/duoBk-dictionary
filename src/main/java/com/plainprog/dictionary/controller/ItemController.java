package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.Constants;
import com.plainprog.dictionary.model.ItemWithTranslationsModel;
import com.plainprog.dictionary.model.MoveItemModel;
import com.plainprog.dictionary.model.db.SharedItem;
import com.plainprog.dictionary.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/dictionary/item")
public class ItemController {
    @Autowired
    ItemService service;

    @RequestMapping(method = RequestMethod.POST, value = "/Create")
    public ResponseEntity createItem(@RequestBody ItemWithTranslationsModel item){
        service.createItem(item);
        return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/CreateShared")
    public ResponseEntity createSharedItem(@RequestBody SharedItem sharedItem){
        if(service.createSharedItem(sharedItem))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Move")
    public ResponseEntity move(@RequestBody MoveItemModel moveItemModel){
        if(service.moveItemToSection(moveItemModel))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Edit")
    public ResponseEntity edit(@RequestBody ItemWithTranslationsModel item){
        if(service.edit(item))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteTranslation")
    public ResponseEntity deleteTranslation(@RequestBody Integer translationId){
        if(service.deleteTranslation(translationId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Delete")
    public ResponseEntity delete(@RequestBody Integer itemId){
        if(service.deleteItem(itemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteShared")
    public ResponseEntity deleteShared(@RequestBody Integer sharedItemId){
        if(service.deleteSharedItem(sharedItemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ChangePublicity")
    public ResponseEntity changePublicity(@RequestBody Integer itemId){
        if(service.changePublicity(itemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
}
