package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.Constants;
import com.plainprog.dictionary.model.ItemModel;
import com.plainprog.dictionary.model.ItemWithTranslationsModel;
import com.plainprog.dictionary.model.MoveItemModel;
import com.plainprog.dictionary.model.SectionModel;
import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.model.db.SharedItem;
import com.plainprog.dictionary.service.DictionaryService;
import com.plainprog.dictionary.service.ItemService;
import com.plainprog.dictionary.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/item")
public class ItemController {
    @Autowired
    ItemService service;
    @Autowired
    SectionService sectionService;
    @Autowired
    DictionaryService dictService;

    @RequestMapping(method = RequestMethod.POST, value = "/Create")
    public ResponseEntity<ItemModel> createItem(@RequestHeader(value = "access") String accessId, @RequestBody ItemWithTranslationsModel item){
        Integer dictId = dictService.getDictIdByAccessToken(accessId);
        if (dictId == null){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        if (item.getItem().getSectionId() == null){
            // if section == null it's unsectioned word, find fake section to put it in
            Integer fakeSectionId = sectionService.getFakeSectionId(dictId);
            if (fakeSectionId == null){
                Section section = sectionService.createFakeSection(dictId);
                fakeSectionId = section.getId();
            }
            item.getItem().setSectionId(fakeSectionId);
        }
        ItemModel model = service.createItem(item);
        return new ResponseEntity<ItemModel>(model,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/Edit")
    public ResponseEntity<ItemModel> edit(@RequestHeader(value = "access") String accessId, @RequestBody ItemWithTranslationsModel item){
        ItemModel model = service.edit(item);
        if(model != null)
            return new ResponseEntity<>(model,HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
    }
    @RequestMapping(value = "/ModifyBatch",method = RequestMethod.POST)
    public ResponseEntity<String> modifyItems(@RequestHeader(value = "access") String accessId, @RequestBody ArrayList<Item> items) {
        boolean success = service.modifyItemBatch(items);
        if (success)  return new ResponseEntity<String>("DONE", HttpStatus.OK);
        else return new ResponseEntity<String>("FAIL", HttpStatus.NOT_MODIFIED);
    }
/*    @RequestMapping(method = RequestMethod.POST, value = "/CreateShared")
    public ResponseEntity createSharedItem(@RequestBody SharedItem sharedItem){
        if(service.createSharedItem(sharedItem))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }*/

/*    @RequestMapping(method = RequestMethod.PUT, value = "/Move")
    public ResponseEntity move(@RequestBody MoveItemModel moveItemModel){
        if(service.moveItemToSection(moveItemModel))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }*/


    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteTranslation")
    public ResponseEntity deleteTranslation(@RequestHeader(value = "access") String accessId, @RequestBody Integer translationId){
        if(service.deleteTranslation(translationId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/Delete")
    public ResponseEntity delete(@RequestHeader(value = "access") String accessId, @RequestBody Integer itemId){
        if(service.deleteItem(itemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }

/*    @RequestMapping(method = RequestMethod.DELETE, value = "/DeleteShared")
    public ResponseEntity deleteShared(@RequestBody Integer sharedItemId){
        if(service.deleteSharedItem(sharedItemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }*/

/*    @RequestMapping(method = RequestMethod.PUT, value = "/ChangePublicity")
    public ResponseEntity changePublicity(@RequestBody Integer itemId){
        if(service.changePublicity(itemId))
            return new ResponseEntity<>(Constants.MESSAGE200,HttpStatus.OK);
        return new ResponseEntity<>(Constants.MESSAGE304, HttpStatus.NOT_MODIFIED);
    }*/
    @RequestMapping(method = RequestMethod.GET, value = "/Get")
    public ResponseEntity getItem(@RequestHeader(value = "access") String accessId, @RequestParam(value = "id",required = true) Integer id ){
        ItemModel itemModel = service.getModel(id);
        if(itemModel== null)
            return new ResponseEntity<>(Constants.MESSAGE204, HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(itemModel,HttpStatus.OK);
    }
}
