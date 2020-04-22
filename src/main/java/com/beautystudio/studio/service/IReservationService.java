package com.beautystudio.studio.service;

import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;

import java.util.List;

public interface IReservationService {
    void save(Reservation reservation, User user);
    List<Reservation> showAllReservationWithStatusFalse();
}
