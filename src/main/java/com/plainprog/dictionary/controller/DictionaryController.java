package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.model.db.Dictionary;
import com.plainprog.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping(path="/dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService service;
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createDict(@RequestBody Dictionary dictionary) throws IOException, ParseException {
        service.create(dictionary);
        return "SUCCESS";
    }
}
