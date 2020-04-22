package com.beautystudio.studio.config;

import com.beautystudio.studio.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Scheduler {
    @Autowired
    private ReservationRepository reservationRepository;

    @Scheduled(cron = "15 * * * * ?")
    public void deleteReservationWhenDateTimeIsOlder(){
        LocalDateTime localDateTime = LocalDateTime.now();
        reservationRepository.deleteByStatusTrueAndDateTimeBefore(localDateTime);
    }
}
