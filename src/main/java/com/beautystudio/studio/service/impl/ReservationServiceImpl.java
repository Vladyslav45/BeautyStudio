package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.ReservationRepository;
import com.beautystudio.studio.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public void save(Reservation reservation, User user) {
        reservation.setStatus(false);
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> showAllReservationWithStatusFalse() {
        return reservationRepository.findAllByStatusFalse();
    }

    @Override
    public void confirmReservation(Long id) {
        reservationRepository.findById(id).ifPresent(r -> {
            r.setStatus(true);
            //TODO W tym miejscu implementacja wiadomości SMS że jest potwierdzona.
            reservationRepository.save(r);
        });
    }


}
