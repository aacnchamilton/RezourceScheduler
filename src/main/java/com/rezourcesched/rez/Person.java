package com.rezourcesched.rez;

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
  private Status status;
  
}
