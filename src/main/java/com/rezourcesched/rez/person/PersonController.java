package com.rezourcesched.rez.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.rezourcesched.rez.entity.Person;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PersonController implements iPersonController {
  
  @Autowired
  PersonService personService;
  
  @Override
  public List<Person> fetchPerson(Long personId, String email, String phone, String firstName,
      String lastName) {
    log.info("PersonController.fetchPerson: personId = {}, email = {}, phone = {}, firstName = {}, lastName = {}"
      , personId, email, phone, firstName, lastName);
    return personService.fetchPerson(personId, email, phone, firstName, lastName);
  }

}
