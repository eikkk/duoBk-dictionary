package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.Constants;
import com.plainprog.dictionary.model.DictionaryModel;
import com.plainprog.dictionary.model.db.Dictionary;
import com.plainprog.dictionary.model.db.SharedDictionary;
import com.plainprog.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService service;

    @RequestMapping(value = "/Create",method = RequestMethod.POST)
    public ResponseEntity createDict(@RequestBody Dictionary dictionary) {
        service.createDict(dictionary);
        return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
    }

    @RequestMapping(value = "/CreateShared",method = RequestMethod.POST)
    public ResponseEntity createSharedDict(@RequestBody SharedDictionary sharedDictionary){
        if(service.createSharedDict(sharedDictionary))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304,HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ChangePublicity")
    public ResponseEntity ChangePublicity(@RequestBody Integer dictId){
        boolean changed = service.changePublicity(dictId);
        if(changed)
            return new ResponseEntity<>(Constants.MESSAGE200, HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304,HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteShared")
    public ResponseEntity deleteShared(@RequestBody Integer sharedDictId){
        if(service.deleteSharedDict(sharedDictId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Get")
    public ResponseEntity getDict(@RequestParam(value = "id",required = true) Integer id ){
        DictionaryModel dictionaryModel = service.getModel(id);
        if(dictionaryModel== null)
            return new ResponseEntity<>(Constants.MESSAGE204, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(dictionaryModel,HttpStatus.OK);
    }
}
