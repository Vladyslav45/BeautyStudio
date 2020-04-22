package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.config.TwilioConfiguration;
import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.ReservationRepository;
import com.beautystudio.studio.service.IReservationService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    private User user;

    private final TwilioConfiguration twilioConfiguration;

    public ReservationServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

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
            PhoneNumber to = new PhoneNumber(user.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
         MessageCreator creator = Message.creator(to,from,"Rezerwacja");
         creator.create();
            reservationRepository.save(r);
        });
    }




}
