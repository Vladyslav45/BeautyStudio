package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.model.Role;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.RoleRepository;
import com.beautystudio.studio.repository.UserRepository;
import com.beautystudio.studio.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void saveUser(User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role = roleRepository.findByRoleType("ROLE_USER");
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void update(User user) {
        userRepository.findById(user.getId()).ifPresent(updateUser -> {
            updateUser.setName(user.getName());
            updateUser.setSurname(user.getSurname());
            updateUser.setEmail(user.getEmail());
            updateUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(updateUser);
        });
    }
}
