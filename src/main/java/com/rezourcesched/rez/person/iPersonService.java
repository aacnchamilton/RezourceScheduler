package com.rezourcesched.rez.person;

import java.util.List;
import com.rezourcesched.rez.entity.Person;

public interface iPersonService {
  
  List<Person> fetchPerson(Long personId, String email, String phone, String firstName, String lastName);
  
  Person createPerson(Person person);
  
  Person revisePerson(Person person);
  
  String deletePerson(Person person);

}
