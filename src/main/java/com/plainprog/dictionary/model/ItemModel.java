package com.plainprog.dictionary.model;

import java.util.ArrayList;

public class ItemModel {
    private Integer id;
    private Integer sectionId;
    private Boolean isPublic;
    private TranslationModel original;
    private ArrayList<TranslationModel> translations;

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

    public ArrayList<TranslationModel> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<TranslationModel> translations) {
        this.translations = translations;
    }
}
