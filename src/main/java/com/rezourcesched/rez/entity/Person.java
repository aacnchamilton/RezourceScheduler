package com.rezourcesched.rez.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
  private Long personId;
  private String firstName;
  private String lastName;
  private String middleName;
  private String email;
  private String phone;
  private String countryCode;
  private String addr1;
  private String addr2;
  private String city;
  private String state;
  private String province;
  private String postalCode;
  private String country;
  private String billing;
  private Status status;
  
}
