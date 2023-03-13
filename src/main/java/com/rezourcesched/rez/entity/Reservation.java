package com.rezourcesched.rez.entity;

import java.sql.Date;
import java.sql.Time;
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
  private Date dateFrom;
  private Date dateTo;
  private Time startTime;
  private Time endTime;
  private int travelTime;
  private int numOfPeople;
  private String specialRequests;
  private ReservationStatus status;
}
