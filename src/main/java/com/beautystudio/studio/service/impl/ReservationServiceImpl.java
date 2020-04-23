package com.beautystudio.studio.service.impl;

import com.beautystudio.studio.config.JavaSenderMail;
import com.beautystudio.studio.config.TwilioConfiguration;
import com.beautystudio.studio.model.Reservation;
import com.beautystudio.studio.model.User;
import com.beautystudio.studio.repository.ReservationRepository;
import com.beautystudio.studio.service.IReservationService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JavaSenderMail javaSenderMail;

    @Autowired
    private TwilioConfiguration twilioConfiguration;

    @Override
    public void save(Reservation reservation, User user) {
        reservation.setStatus(false);
        reservation.setUser(user);
        reservationRepository.save(reservation);
        javaSenderMail.sendEmail(user, reservation.getDateTime(), reservation.getSubCategory());
    }

    @Override
    public List<Reservation> showAllReservationWithStatusFalse() {
        return reservationRepository.findAllByStatusFalse();
    }

    @Override
    public void confirmReservation(Long id) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a");
        reservationRepository.findById(id).ifPresent(r -> {
            r.setStatus(true);
            Message.creator(
                    new PhoneNumber(r.getUser().getPhoneNumber()),
                    new PhoneNumber(twilioConfiguration.getTrialNumber()),
                    "Twoja rezerwacja na: " + r.getSubCategory().getName() + " w dniu " + dateTimeFormatter.format(r.getDateTime()) + " jest przyjeta").create();
            reservationRepository.save(r);
        });
    }




}
