package com.plainprog.dictionary.model;

import javax.persistence.*;

@Entity
@Table(name = "shared_item")
public class SharedItem {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Column(name = "wasOpened")
    private  Boolean wasOpened;

    public SharedItem() {
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
