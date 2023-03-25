package com.rezourcesched.rez.reservation;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.rezourcesched.rez.entity.Person;
import com.rezourcesched.rez.entity.Reservation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReservationController implements iReservationController {

  @Autowired
  ReservationService reservationService;
  
  @Override
  public List<Reservation> fetchReservation(Long reservationId, Long rezourceId, Long schedulerId, Date dateFrom,
      Date dateTo) {
    log.info("ReservationController.fetchReservation: reservationId = {}, rezourceId = {}, schedulerId = {}, dateFrom = {}, dateTo{}"
        , reservationId, rezourceId, schedulerId, dateFrom, dateTo);

    return reservationService.fetchReservation(reservationId, rezourceId, schedulerId, dateFrom, dateTo);
  }

  @Override
  public Reservation createReservation(Reservation reservation) {
    log.info("ReservationController.createReservation: reservation = {}",reservation);
    return reservationService.createReservation(reservation);
  }

}
