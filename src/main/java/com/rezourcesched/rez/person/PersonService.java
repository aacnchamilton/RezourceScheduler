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

}
