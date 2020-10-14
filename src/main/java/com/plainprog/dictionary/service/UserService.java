package com.plainprog.dictionary.service;

import com.plainprog.dictionary.model.db.User;
import com.plainprog.dictionary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public List<User> findByAccessId(String accessId){
        return repository.findByAccessId(accessId);
    }
}
