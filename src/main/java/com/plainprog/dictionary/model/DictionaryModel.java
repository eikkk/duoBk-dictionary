package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.Section;

import java.util.ArrayList;
import java.util.List;

public class DictionaryModel {
    private Integer id;
    private Integer ownerId;
    private boolean isPublic;
    private ArrayList<Section> sections;

    public DictionaryModel() {
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

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }
}
