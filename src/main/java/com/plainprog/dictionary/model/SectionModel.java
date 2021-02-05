package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.Section;

import java.util.ArrayList;

public class SectionModel {
    private Integer id;
    private Integer dictId;
    private String name;
    private Boolean isPublic;
    private Boolean fake;
    private ArrayList<ItemModel> items;

    public SectionModel() {
    }

    public SectionModel(Integer id, Integer dictId, String name, Boolean isPublic, ArrayList<ItemModel> items) {
        this.id = id;
        this.dictId = dictId;
        this.name = name;
        this.isPublic = isPublic;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public ArrayList<ItemModel> getItems() {
        return items;
    }

    public void setItems(ArrayList<ItemModel> items) {
        this.items = items;
    }

    public Boolean getFake() {
        return fake;
    }

    public void setFake(Boolean fake) {
        this.fake = fake;
    }
}
