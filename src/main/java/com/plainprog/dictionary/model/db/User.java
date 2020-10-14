package com.plainprog.dictionary.model.db;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table (name = "user")
public class User {
    public User() {
    }

    public User(String mail, String userType) {
        this.mail = mail;
        this.userType = userType;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "mail")
    private String mail;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "is_on_mobile")
    private Boolean isOnMobile;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "access_id")
    private String accessId;


    public Integer getId() { return id; }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }


    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getOnMobile() { return isOnMobile; }
    public void setOnMobile(Boolean onMobile) { isOnMobile = onMobile; }

    public String getFacebookId() { return facebookId; }
    public void setFacebookId(String facebookId) { this.facebookId = facebookId; }

    public String getAccessId() { return accessId; }
    public void setAccessId(String accessId) { this.accessId = accessId; }
}
