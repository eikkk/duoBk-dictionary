package com.plainprog.dictionary.model;

import javax.persistence.*;

@Entity
@Table(name = "shared_dict")
public class SharedDictionary {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dict_id")
    private Integer dictId;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Column(name = "wasOpened")
    private Boolean wasOpened;

    public SharedDictionary() {
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

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Boolean getWasOpened() {
        return wasOpened;
    }

    public void setWasOpened(Boolean wasOpened) {
        this.wasOpened = wasOpened;
    }
}
