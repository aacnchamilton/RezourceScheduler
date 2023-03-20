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

  @Override
  public Person createPerson(Person person) {
    log.info("PersonController.createPerson: person = {}", person);
    return personService.createPerson(person);
  }

  @Override
  public Person revisePerson(Person person) {
    log.info("PersonController.revisePerson: person = {}", person);
    return personService.revisePerson(person);
  }

  @Override
  public String deletePerson(Person person) {
    log.info("PersonController.deletePerson: person = {}", person);
    return personService.deletePerson(person);
  }

}
