package com.rezourcesched.rez.reservation;

import java.util.Date;
import java.util.List;
import com.rezourcesched.rez.entity.Person;
import com.rezourcesched.rez.entity.Reservation;

public interface iReservationService {

  List<Reservation> fetchReservation(Long reservationId, Long rezourceId, Long schedulerId, Date dateFrom,
      Date dateTo);
  
  Reservation createReservation(Reservation reservation);
  
}
