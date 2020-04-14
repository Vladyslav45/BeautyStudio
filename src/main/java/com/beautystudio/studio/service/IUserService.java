package com.beautystudio.studio.service;

import com.beautystudio.studio.model.User;

public interface IUserService {
    void saveUser(User user);
    User findByEmail(String email);
}