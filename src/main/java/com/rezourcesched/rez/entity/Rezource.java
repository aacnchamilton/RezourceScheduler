package com.rezourcesched.rez.entity;

import java.sql.Time;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rezource {
  private Long rezourceId;
  private Long rezourcerId; 
  private RezourceType rezourceType;
  private ScheduleType scheduleType;
  private Status status;
  private Time startTime;
  private Time endTime;
  private double minimum;
  private double regularRate;
  private double weekendRate;
  private double deposit;
  private double cleaningFee;
  private double travelRate;
  private double emergRate;
  private int sizeInFeet;
  private int people;
  private int year;
  private int bedrooms;
  private int bathrooms;
  private int beds;
  private String rezourceName;
  private String rezourceDescription;
  private String style;
  private String manufacturer;
  private String model;
  private String keywords;
  private String addr1;
  private String addr2;
  private String city;
  private String state;
  private String province;
  private String postalCode;
  private String country;
  private String billing;
}
