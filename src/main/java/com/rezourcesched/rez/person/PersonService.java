package com.rezourcesched.rez.person;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rezourcesched.rez.entity.Person;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService implements iPersonService {
  
  @Autowired
  PersonDao personDao;

  @Override
  public List<Person> fetchPerson(Long personId, String email, String phone, String firstName,
      String lastName) {
    log.info("PersonService.fetchPerson: personId = {}, email = {}, phone = {}, firstName = {}, lastName = {}"
        , personId, email, phone, firstName, lastName);
    return personDao.fetchPerson(personId, email, phone, firstName, lastName);
  }

  @Override
  public Person createPerson(Person person) {
    log.info("PersonService.createPerson: person = {}", person);
    return personDao.createPerson(person);
  }

  @Override
  public Person revisePerson(Person person) {
    log.info("PersonService.revisePerson: person = {}", person);
    return personDao.revisePerson(person);
  }

  @Override
  public String deletePerson(Person person) {
    log.info("PersonService.deletePerson: person = {}", person);
    return personDao.deletePerson(person);
  }

}
