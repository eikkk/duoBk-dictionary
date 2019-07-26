package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.Section;

import java.util.ArrayList;
import java.util.List;

public class DictionaryModel {
    private Integer id;
    private Integer ownerId;
    private boolean isPublic;
    private ArrayList<SectionModel> sections;

    public DictionaryModel() {
    }

    public DictionaryModel(Integer id, Integer ownerId, boolean isPublic, ArrayList<SectionModel> sections) {
        this.id = id;
        this.ownerId = ownerId;
        this.isPublic = isPublic;
        this.sections = sections;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public ArrayList<SectionModel> getSections() {
        return sections;
    }

    public void setSections(ArrayList<SectionModel> sections) {
        this.sections = sections;
    }
}
