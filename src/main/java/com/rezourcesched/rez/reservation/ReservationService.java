package com.rezourcesched.rez.reservation;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rezourcesched.rez.entity.Reservation;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReservationService implements iReservationService {
  
  @Autowired
  ReservationDao reservationDao;

  @Override
  public List<Reservation> fetchReservation(Long reservationId, Long rezourceId, Long schedulerId, Date dateFrom,
      Date dateTo) {
    log.info("ReservationService.fetchReservation: reservationId = {}, resourceId = {}, schedulerId = {}, dateFrom = {}, dateTo{}"
        , reservationId, rezourceId, schedulerId, dateFrom, dateTo);
    return reservationDao.fetchReservation(reservationId, rezourceId, schedulerId, dateFrom, dateTo);
  }

  @Override
  public Reservation createReservation(Reservation reservation) {
    log.info("ReservationService.createReservation: reservation = {}", reservation);
    return reservationDao.createReservation(reservation);
  }

}
