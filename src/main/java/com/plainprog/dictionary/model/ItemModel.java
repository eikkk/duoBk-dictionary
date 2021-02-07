package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.ItemTranslation;

import java.util.ArrayList;

public class ItemModel {
    private Integer id;
    private Integer sectionId;
    private Boolean isPublic;
    private TranslationModel original;
    private Integer sortIndex;
    private ArrayList<ItemTranslation> translations;

    public ItemModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public TranslationModel getOriginal() {
        return original;
    }

    public void setOriginal(TranslationModel original) {
        this.original = original;
    }

    public ArrayList<ItemTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<ItemTranslation> translations) {
        this.translations = translations;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }
}
