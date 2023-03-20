package com.rezourcesched.rez.person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    return selectPerson(personId, email, phone, firstName, lastName);
  }
  
  @Override
  public Person createPerson(Person person) {
    log.info("PersonDao.createPerson: person = {}", person);
    Long personId = insertPerson(person);
    log.info("PersonDao.createPerson: Newly Created personId: {}", personId);
    List<Person> personCreated = fetchPerson(personId, null, null, null, null);
    return personCreated.get(0);
  }
  
  @Override
  public Person revisePerson(Person person) {
    log.info("PersonDao.updatePerson: person = {}", person);
    Long personId = updatePerson(person);
    log.info("PersonDao.updatePerson: Newly Updated personId: {}", personId);
    List<Person> personUpdated = fetchPerson(personId, null, null, null, null);
    return personUpdated.get(0);
  }

  @Override
  public String deletePerson(Person person) {
    String sql = "DELETE FROM person WHERE person_id = :personId";
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("personId", person.getPersonId());
    log.info("PersonDao.deletePerson: sql = {}, parameters = {}", sql, params);
    jdbcTemplate.update(sql, params);
    return "Person record with id = " + person.getPersonId()+ "deleted successfully";
  }
  private Long insertPerson(Person person) {     
    String sql = "INSERT INTO person ("
        + "first_name, last_name, middle_name, email, phone, country_code, "
        + "addr1, addr2, city, state, province, postal_code, country, billing, status )"
        + " VALUES ("
        + ":firstName, :lastName, :middleName, :email, :phone, :countryCode, "
        + ":addr1, :addr2, :city, :state, :province, :postalCode, :country, :billing, :status )";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("firstName", person.getFirstName());
    params.addValue("lastName", person.getLastName());
    params.addValue("middleName", person.getMiddleName());
    params.addValue("email", person.getEmail());
    params.addValue("phone", person.getPhone());
    params.addValue("countryCode", person.getCountryCode());
    params.addValue("addr1", person.getAddr1());
    params.addValue("addr2", person.getAddr2());
    params.addValue("city", person.getCity());
    params.addValue("state", person.getState());
    params.addValue("province", person.getProvince());
    params.addValue("postalCode", person.getPostalCode());
    params.addValue("country", person.getCountry());
    params.addValue("billing", person.getBilling());
    params.addValue("status", person.getStatus().toString());
    log.info("PersonDao.insertPerson: sql = {}, parameters = {}", sql, params);
    jdbcTemplate.update(sql, params, keyHolder);
    
    return keyHolder.getKey().longValue();
  }

  private Long updatePerson(Person person) {
    MapSqlParameterSource params = new MapSqlParameterSource();
    
    /*** Construct the Update SQL based on what values were available in the PUT Body ***/
    /*** There has to be a more efficient way of doing this... for right now I want to get it done... will look for efficiency later ***/
    String sql = "UPDATE person set ";
    if (!(person.getFirstName() == null)) {
      sql += "first_name = :firstName, ";
      params.addValue("firstName", person.getFirstName());
    }
    if (!(person.getLastName() == null)) {
      sql += "last_name = :lastName, ";
      params.addValue("lastName", person.getLastName());
    }
    if (!(person.getMiddleName() == null)) {
      sql += "middle_name = :middleName, ";
      params.addValue("middleName", person.getMiddleName());
    }
    if (!(person.getPhone() == null)) {
      sql += "phone = :phone, ";
      params.addValue("phone", person.getPhone());
    }
    if (!(person.getEmail() == null)) {
      sql += "email = :email, ";
      params.addValue("email", person.getEmail());
    }
    if (!(person.getStatus() == null)) {
      sql += "status = :status, ";
      params.addValue("status", person.getStatus());
    }
    if (!(person.getAddr1() == null)) {
      sql += "addr1 = :addr1, ";
      params.addValue("addr1", person.getAddr1());
    }
    if (!(person.getAddr2() == null)) {
      sql += "addr2 = :addr2, ";
      params.addValue("addr2", person.getAddr2());
    }
    if (!(person.getCity() == null)) {
      sql += "city = :city, ";
      params.addValue("city", person.getCity());
    }
    if (!(person.getState() == null)) {
      sql += "state = :state, ";
      params.addValue("state", person.getState());
    }
    if (!(person.getProvince() == null)) {
      sql += "province = :province, ";
      params.addValue("province", person.getProvince());
    }
    if (!(person.getPostalCode() == null)) {
      sql += "postal_code = :postalCode, ";
      params.addValue("postalCode", person.getPostalCode());
    }
    if (!(person.getCountry() == null)) {
      sql += "country = :country, ";
      params.addValue("country", person.getCountry());
    }
    if (!(person.getBilling() == null)) {
      sql += "billing = :billing, ";
      params.addValue("billing", person.getBilling());
    }
    sql = sql.substring(0, sql.length()-2);  //Need to remove the ending comma and space from the SQL string
    sql += " where person_id = :personId";  //Can only update a Person record by the personId, must have the personId in the request body
    params.addValue("personId", person.getPersonId());
    log.info("PersonDao.updatePerson: sql = {}, parameters = {}", sql, params);
    jdbcTemplate.update(sql, params);
    return person.getPersonId();
  }
  
  private List<Person> selectPerson(Long personId, String email, String phone, String firstName,String lastName) {
    String sql = "SELECT * FROM person WHERE 1=1";  //where 1=1 allows me to construct additional optional bind variables
    Map<String, Object> params = new HashMap<>();
    if (!(personId == null)) {
      sql += " and person_id = :personId";
      params.put("personId", personId.toString());
    }
    if (!(email == null)) {  //if email is not null, then search by email
      sql += " and email = :email";
      params.put("email", email);
    }
    if (!(phone == null)) {  //if phone is not null, then search by phone
      sql += " and phone = :phone";
      params.put("phone", phone);
    }
    if (!(firstName == null)) {  //if first name is not null, then search by first name
      sql += " and first_name = :firstName";
      params.put("firstName", firstName);
    }
    if (!(lastName == null)) {  //if last name is not null, then search by last name
      sql += " and last_name = :lastName";
      params.put("lastName", lastName);
    }
    
    log.info("PersonDao.selectPerson: sql = {}, params = {}", sql, params);
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
