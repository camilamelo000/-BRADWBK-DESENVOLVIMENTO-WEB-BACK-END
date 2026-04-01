package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {

    void add(User newUser);

    User find(String login);

    boolean remove(String login);
}