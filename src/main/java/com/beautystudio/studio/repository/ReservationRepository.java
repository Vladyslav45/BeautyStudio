package com.beautystudio.studio.repository;

import com.beautystudio.studio.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByStatusFalse();

    Reservation findByUserId(Long id);

    @Modifying
    @Transactional
    void deleteByStatusTrueAndDateTimeBefore(LocalDateTime localDateTime);
}
