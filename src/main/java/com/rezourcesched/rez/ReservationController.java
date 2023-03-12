package com.rezourcesched.rez;

import java.util.Date;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.rezourcesched.rez.entity.Reservation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ReservationController implements iReservationController {

  @Override
  public List<Reservation> fetchReservation(Long resourceId, String schedulerId, Date dateFrom,
      Date dateTo) {
    log.info("ReservationController.fetchReservation: resourceId = {}, schedulerId = {}, dateFrom = {}, dateTo{}"
        , resourceId, schedulerId, dateFrom, dateTo);

    return null;
  }

}
