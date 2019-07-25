package com.plainprog.dictionary.model;

import com.plainprog.dictionary.model.db.Item;
import com.plainprog.dictionary.model.db.ItemTranslation;

import java.util.List;
public class ItemWithTranslationsModel {
    private Item item;
    private List<ItemTranslation> translations;

    public ItemWithTranslationsModel() {
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<ItemTranslation> translations) {
        this.translations = translations;
    }
}
