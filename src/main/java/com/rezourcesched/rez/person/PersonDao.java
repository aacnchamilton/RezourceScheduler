package com.rezourcesched.rez.person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rezourcesched.rez.entity.Person;
import com.rezourcesched.rez.entity.Status;
import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j
public class PersonDao implements iPersonDao {
  
  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Person> fetchPerson(Long personId, String email, String phone, String firstName,
      String lastName) {
    log.info("PersonDao.fetchPerson: personId = {}, email = {}, phone = {}, firstName = {}, lastName = {}"
        , personId, email, phone, firstName, lastName);
    String sql = "SELECT * FROM person WHERE 1=1";  //where 1=1 allows me to construct additional optional bind variables
    Map<String, Object> params = new HashMap<>();
    if (!(personId == null)) {
      sql += " and person_id = :personId";
      params.put("personId", personId.toString());
    }
    if (!(email == null)) {  //if name is not null
      sql += " and email = :email";
      params.put("email", email);
    }
    if (!(phone == null)) {  //if name is not null
      sql += " and phone = :phone";
      params.put("phone", phone);
    }
    if (!(firstName == null)) {  //if name is not null
      sql += " and first_name = :firstName";
      params.put("firstName", firstName);
    }
    if (!(lastName == null)) {  //if name is not null
      sql += " and last_name = :lastName";
      params.put("lastName", lastName);
    }
    
    log.info("PersonDao.fetchPerson: sql = {}, params = {}", sql, params);
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      
      @Override
      public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Person.builder()
            .personId(rs.getLong("person_id"))
            .firstName(rs.getString("first_name"))
            .lastName(rs.getString("last_name"))
            .middleName(rs.getString("middle_name"))
            .email(rs.getString("email"))
            .phone(rs.getString("phone"))
            .countryCode(rs.getString("country_code"))
            .addr1(rs.getString("addr1"))
            .addr2(rs.getString("addr2"))
            .city(rs.getString("city"))
            .state(rs.getString("state"))
            .province(rs.getString("province"))
            .postalCode(rs.getString("postal_code"))
            .country(rs.getString("country"))
            .billing(rs.getString("billing"))  //needed charAt(0) because of the MySQL data type Char is really just a string
            .status(Status.valueOf(rs.getString("status")))
            .build();
        // @formatter:on
      }
    });
  }

}
