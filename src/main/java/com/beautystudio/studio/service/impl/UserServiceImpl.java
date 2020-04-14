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
        Role role = new Role();
        role.setRoleType("ROLE_USER");
        role.setUser(user);
        userRepository.save(user);
        roleRepository.save(role);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
