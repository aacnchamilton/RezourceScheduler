package com.rezourcesched.rez;

import java.util.List;
import com.rezourcesched.rez.entity.Person;

public interface iPersonService {
  
  List<Person> fetchPerson(Long personId, String email, String phone, String firstName, String lastName);

}
