package com.beautystudio.studio.service;

import com.beautystudio.studio.model.User;

import java.util.Map;

public interface IUserService {
    void saveUser(User user);
    User findByEmail(String email);
    User findById(Long userId);
    void update(User user);
    void forgetPassword(User user,Map<String, String> requestParam);
    void saveToken(User user);
    User findResetToken(String token);
}