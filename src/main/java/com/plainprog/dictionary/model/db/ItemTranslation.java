package com.plainprog.dictionary.model.db;

import javax.persistence.*;

@Entity
@Table(name = "dict_item_translation")
public class ItemTranslation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dict_item_id")
    private Integer itemId;
    @Column(name = "lang")
    private String lang;
    @Column(name = "value")
    private String value;
    @Column(name = "note")
    private String note;

    public ItemTranslation() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
