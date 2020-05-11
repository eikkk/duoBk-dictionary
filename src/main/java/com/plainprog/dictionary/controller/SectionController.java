package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.Constants;
import com.plainprog.dictionary.model.SectionModel;
import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.SharedSection;
import com.plainprog.dictionary.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/section")
public class SectionController {
    @Autowired
    SectionService service;

    @RequestMapping(value = "/Create",method = RequestMethod.POST)
    public ResponseEntity createSection(@RequestBody Section section) {
        service.createSection(section);
        return new ResponseEntity<>(Constants.MESSAGE200, HttpStatus.OK);
    }

    @RequestMapping(value = "/CreateShared",method = RequestMethod.POST)
    public ResponseEntity createSharedSection(@RequestBody SharedSection sharedSection){
        if(service.createSharedSection(sharedSection))
            return new ResponseEntity<>(Constants.MESSAGE200, HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(value = "/ChangeName",method = RequestMethod.PUT)
    public ResponseEntity changeSectionName(@RequestParam(value = "sectionId", required = true) Integer sectionId, @RequestParam(value = "name",required = true) String newName){
        if(service.changeSectionName(sectionId,newName))
            return new ResponseEntity<>(Constants.MESSAGE200, HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/Delete")
    public ResponseEntity delete(@RequestBody Integer sectionId){
        if(service.deleteSection(sectionId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteShared")
    public ResponseEntity deleteShared(@RequestBody Integer sharedSectionId){
        if(service.deleteSharedSection(sharedSectionId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/ChangePublicity")
    public ResponseEntity changePublicity(@RequestBody Integer sectionId){
        if(service.changePublicity(sectionId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/Get")
    public ResponseEntity getSection(@RequestParam(value = "id",required = true) Integer id ){
        SectionModel sectionModel = service.getModel(id);
        if(sectionModel== null)
            return new ResponseEntity<>(Constants.MESSAGE204, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(sectionModel,HttpStatus.OK);
    }
}
