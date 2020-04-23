package com.beautystudio.studio.service;

import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;

import java.util.List;

public interface IReservationService {
    void save(Reservation reservation, User user);
    List<Reservation> showAllReservationWithStatusFalse();
    void confirmReservation(Long id);
    List<Reservation> showAllUserReservation(Long userId);
    void deleteReservation(Long id);
    Reservation findById(Long id);
    void update(Reservation reservation);
}
