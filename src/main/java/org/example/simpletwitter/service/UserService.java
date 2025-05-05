package org.example.simpletwitter.service;

import org.example.simpletwitter.model.User;

public interface UserService {
    User findByUsername(String username);

    User save(User userDto);
}
