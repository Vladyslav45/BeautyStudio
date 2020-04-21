package com.beautystudio.studio.service;

import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;

public interface IReservationService {
    void save(Reservation reservation, User user);
}
