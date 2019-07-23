package com.plainprog.dictionary.model.db;

import javax.persistence.*;

@Entity
@Table(name = "shared_section")
public class SharedSection {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "section_id")
    private Integer sectionId;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @Column(name = "wasOpened")
    private Boolean wasOpened;

    public SharedSection() {
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
