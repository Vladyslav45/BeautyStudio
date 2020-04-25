package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void update() {
        User memoryUser = inMemoryUser();
        UserRepository memoryUserRepository = inMemoryUserRepository();
        long countBeforeCall =  memoryUserRepository.count();
        memoryUserRepository.save(memoryUser);

        memoryUserRepository.findById(memoryUser.getId()).ifPresent(updateUser -> {
            updateUser.setName(memoryUser.getName());
            updateUser.setSurname(memoryUser.getSurname());
            updateUser.setEmail(memoryUser.getEmail());
            updateUser.setPhoneNumber(memoryUser.getPhoneNumber());
            memoryUserRepository.save(updateUser);
        });
        assertThat(countBeforeCall+1)
                .isEqualTo(memoryUserRepository.count());
}

    private User inMemoryUser(){
        User user = new User();
        user.setId(1l);
        user.setName("john");
        user.setSurname("McDonald");
        user.setEmail("admin@gmail.com");
        user.setPhoneNumber("+48555222444");
        return user;
    }


    private UserRepository inMemoryUserRepository(){
        UserRepository userRepository = new UserRepository() {
            private long index = 0;
            private Map<Long,User> map = new HashMap<>();
            @Override
            public User findByEmail(String email) {
                return null;
            }

            @Override
            public List<User> findAll() {
                return new ArrayList<>(map.values());
            }

            @Override
            public List<User> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<User> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends User> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends User> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<User> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public User getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<User> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> S save(S s) {
                if (s.getId() != 0){
                    map.put(s.getId(),s);
                }else {
                    map.put(++index,s);
                }
                return s;
            }

            @Override
            public Optional<User> findById(Long aLong) {
                return Optional.ofNullable(map.get(aLong));
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return map.values().size();
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(User user) {

            }

            @Override
            public void deleteAll(Iterable<? extends User> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends User> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends User> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends User> boolean exists(Example<S> example) {
                return false;
            }
        };
        return userRepository;
    }
}