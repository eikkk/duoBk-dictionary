package com.plainprog.dictionary.controller;

import com.plainprog.dictionary.model.db.Section;
import com.plainprog.dictionary.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping(path="/dictionary/section")
public class SectionController {
    @Autowired
    SectionService service;
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createSection(@RequestBody Section section) throws IOException, ParseException {
        service.create(section);
        return "SUCCESS";
    }
}
