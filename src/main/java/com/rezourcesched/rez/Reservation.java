package com.rezourcesched.rez;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
  private Long reservationId;
  private Long rezourceId;
  private Long schedulerId;
  private Long addressId;
  private LocalDate dateFrom;
  private LocalDate dateTo;
  private LocalTime startTime;
  private LocalTime endTime;
  private int travelTime;
  private int numOfPeople;
  private String specialRequests;
  private ReservationStatus status;
}
