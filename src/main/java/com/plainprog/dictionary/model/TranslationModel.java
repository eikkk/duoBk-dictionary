package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.ItemTranslation;

public class TranslationModel {
    private String value;
    private String lang;

    public TranslationModel() {
    }

    public TranslationModel(String value, String lang) {
        this.value = value;
        this.lang = lang;
    }
    public TranslationModel(ItemTranslation translation){
        this.value = translation.getValue();
        this.lang = translation.getLang();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
