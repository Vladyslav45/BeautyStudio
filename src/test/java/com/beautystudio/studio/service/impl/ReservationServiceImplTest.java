package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.Role;
import com.beautystudio.studio.model.SubCategory;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {

    @Test
    void save_Reservation_By_User() {
    User memoryUser = inMemoryUser();
    Reservation memoryReservation = inMemoryReservation();
    ReservationRepository memoryReservationRepository = inMemoryReservationRepository();
    memoryReservation.setStatus(false);
    memoryReservation.setUser(memoryUser);
    memoryReservationRepository.save(memoryReservation);

    assertTrue(!memoryReservationRepository.findAll().isEmpty());
    assertTrue(memoryReservationRepository.findById(memoryReservation.getId()).isPresent());
    }

    private User inMemoryUser(){
        User user = new User();
        user.setName("John");
        user.setId(1l);
        user.setSurname("McDonald");
        user.setEmail("pekalaandrzej50@gmail.com");
        user.setPhoneNumber("+48510285442");
        return user;
    }

    private Reservation inMemoryReservation(){
        Reservation reservation = new Reservation();
        reservation.setId(2l);
        reservation.setStatus(true);
        return reservation;
    }

    private ReservationRepository inMemoryReservationRepository(){
        ReservationRepository reservationRepository = new ReservationRepository() {
            private long index = 0;
            private Map<Long,Reservation> map = new HashMap<>();
            @Override
            public List<Reservation> findAllByStatusFalse() {
                return null;
            }

            @Override
            public List<Reservation> findAllByStatusTrueAndUserId(Long userId) {
                return null;
            }

            @Override
            public void deleteByStatusTrueAndDateTimeBefore(LocalDateTime localDateTime) {

            }

            @Override
            public List<Reservation> findAll() {
                return new ArrayList<>(map.values());
            }

            @Override
            public List<Reservation> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<Reservation> findAllById(Iterable<Long> iterable) {
                return null;
            }

            @Override
            public <S extends Reservation> List<S> saveAll(Iterable<S> iterable) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends Reservation> S saveAndFlush(S s) {
                return null;
            }

            @Override
            public void deleteInBatch(Iterable<Reservation> iterable) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public Reservation getOne(Long aLong) {
                return null;
            }

            @Override
            public <S extends Reservation> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends Reservation> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<Reservation> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Reservation> S save(S s) {
                if (s.getId() != 0){
                    map.put(s.getId(),s);
                }else {
                    map.put(++index,s);
                }
                return s;
            }

            @Override
            public Optional<Reservation> findById(Long aLong) {
                return Optional.ofNullable(map.get(aLong));
            }

            @Override
            public boolean existsById(Long aLong) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(Long aLong) {

            }

            @Override
            public void delete(Reservation reservation) {

            }

            @Override
            public void deleteAll(Iterable<? extends Reservation> iterable) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends Reservation> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends Reservation> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends Reservation> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends Reservation> boolean exists(Example<S> example) {
                return false;
            }
        };
        return reservationRepository;
    }



}