package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.ItemTranslation;

public class TranslationModel {
    private String value;
    private String lang;
    private String note;

    public TranslationModel() {
    }

    public TranslationModel(String value, String lang, String note) {
        this.value = value;
        this.lang = lang;
        this.note = note;
    }
    public TranslationModel(ItemTranslation translation){
        this.value = translation.getValue();
        this.lang = translation.getLang();
        this.note = translation.getNote();
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
