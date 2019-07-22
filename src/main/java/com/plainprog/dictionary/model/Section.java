package com.plainprog.dictionary.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;

@Entity
@Table(name = "dict_section")
public class Section {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dict_id")
    private Integer dictId;
    @Column(name = "name")
    private String name;
    @Column(name = "isPublic")
    private Boolean isPublic;

    public Section() {
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
}
