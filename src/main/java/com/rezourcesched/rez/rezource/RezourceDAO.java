package com.rezourcesched.rez.rezource;

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
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;
import lombok.extern.slf4j.Slf4j;
import com.rezourcesched.rez.entity.ScheduleType;
import com.rezourcesched.rez.entity.Status;

@Component
@Service
@Slf4j
public class RezourceDAO implements iRezourceDAO {
  
  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Rezource> fetchRezource(Long rezourceId, String state, String postalCode, RezourceType rezourceType, Long rezourcerId) {
    log.info("RezourceDAO.fetchRezource rezourceId = {}, state = {}, postalCode = {}, rezourceType = {}, rezourcerId = {}"
        , rezourceId, state, postalCode, rezourceType, rezourcerId);
    /*** At some point add a City Search ***/
    String sql = "SELECT * FROM rezource WHERE 1=1";  //where 1=1 allows me to construct additional optional bind variables
    Map<String, Object> params = new HashMap<>();
    if (!(state == null)) {  //if name is not null
      sql += " and state = :state";
      params.put("state", state);
    }
    if (!(postalCode == null)) { 
      sql += " and postal_code = :postalCode";
      params.put("postalCode", postalCode);
    }
    if (!(rezourceType == null)) { 
      sql += " and rezource_type = :rezourceType";
      params.put("rezourceType", rezourceType.toString());
    }
    if (!(rezourceId == null)) {
      sql += " and rezource_id = :rezourceId";
      params.put("rezourceId", rezourceId.toString());
    }
    if (!(rezourcerId == null)) {
      sql += " and rezourcer_id = :rezourcerId";
      params.put("rezourcerId", rezourcerId.toString());
    }

    log.info("RezourcesDao.fetchRezources: sql = {}, params = {}", sql, params);
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      
      @Override
      public Rezource mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Rezource.builder()
            .rezourceId(rs.getLong("rezource_id"))
            .rezourcerId(rs.getLong("rezourcer_id"))
            .rezourceType(RezourceType.valueOf(rs.getString("rezource_type")))
            .scheduleType(ScheduleType.valueOf(rs.getString("schedule_type")))
            .status(Status.valueOf(rs.getString("status")))
            .keywords(rs.getString("key_words"))
            .startTime(rs.getTimestamp("start_time"))
            .endTime(rs.getTimestamp("end_time"))
            .regularRate(rs.getDouble("regular_rate"))
            .weekendRate(rs.getDouble("weekend_rate"))
            .deposit(rs.getDouble("deposit"))
            .cleaningFee(rs.getDouble("cleaning_fee"))
            .travelRate(rs.getDouble("travel_rate"))
            .emergRate(rs.getDouble("emerg_rate"))
            .sizeInFeet(rs.getInt("size_in_ft"))
            .people(rs.getInt("people"))
            .year(rs.getInt("year"))
            .bedrooms(rs.getInt("bedrooms"))
            .bathrooms(rs.getInt("bathrooms"))
            .beds(rs.getInt("beds"))
            .rezourceName(rs.getString("name"))
            .rezourceDescription(rs.getString("description"))
            .style(rs.getString("style"))
            .manufacturer(rs.getString("manufacturer"))
            .model(rs.getString("model"))
            .addr1(rs.getString("addr1"))
            .addr2(rs.getString("addr2"))
            .city(rs.getString("city"))
            .state(rs.getString("state"))
            .province(rs.getString("province"))
            .postalCode(rs.getString("postal_code"))
            .country(rs.getString("country"))
            .billing(rs.getString("billing"))  //needed charAt(0) because of the MySQL data type Char is really just a string
            .build();
        // @formatter:on
      }
    });
  }

  @Override
  public Rezource createRezource(Rezource rezource) {
    log.info("RezourceDAO.fetchRezource: rezourceId = {}", rezource);
    Long rezourceId = insertRezource(rezource);
    log.info("RezourceDAO.createPerson: Newly created rezourceId: {}", rezourceId);
    List<Rezource> rezourceCreated = fetchRezource(rezourceId, null, null, null, null);
    return rezourceCreated.get(0);
  }

  private Long insertRezource(Rezource rezource) {
    String sql = "INSERT INTO rezource ("
        + "rezourcer_id, rezource_type, schedule_type, status, start_time, end_time, regular_rate, weekend_rate, deposit, cleaning_fee, "
        + "travel_rate, emerg_rate, size_in_ft, people, year, bedrooms, bathrooms, beds, name, description, "
        + "style, manufacturer, model, key_words, addr1, addr2, city, state, province, postal_code, country, billing )"
        + " VALUES ("
        + ":rezourcerId, :rezourceType, :scheduleType, :status, :startTime, :endTime, :regularRate, :weekendRate, :deposit, :cleaningFee, "
        + ":travelRate, :emergRate, :sizeInFeet, :people, :year, :bedrooms, :bathrooms, :beds, :rezourceName, :rezourceDescription, "
        + ":style, :manufacturer, :model, :keywords, :addr1, :addr2, :city, :state, :province, :postalCode, :country, :billing )";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("rezourcerId", rezource.getRezourcerId());
    params.addValue("rezourceType", rezource.getRezourceType().toString());
    params.addValue("scheduleType", rezource.getScheduleType().toString());
    params.addValue("status", rezource.getStatus().toString());
    params.addValue("startTime", rezource.getStartTime());
    params.addValue("endTime", rezource.getEndTime());
    params.addValue("regularRate", rezource.getRegularRate());
    params.addValue("weekendRate", rezource.getWeekendRate());
    params.addValue("deposit", rezource.getDeposit());
    params.addValue("cleaningFee", rezource.getCleaningFee());
    params.addValue("travelRate", rezource.getTravelRate());
    params.addValue("emergRate", rezource.getEmergRate());
    params.addValue("sizeInFeet", rezource.getSizeInFeet());
    params.addValue("people", rezource.getPeople());
    params.addValue("year", rezource.getYear());
    params.addValue("bedrooms", rezource.getBedrooms());
    params.addValue("bathrooms", rezource.getBathrooms());
    params.addValue("beds", rezource.getBeds());
    params.addValue("rezourceName", rezource.getRezourceName());
    params.addValue("rezourceDescription", rezource.getRezourceDescription());
    params.addValue("style", rezource.getStyle());
    params.addValue("manufacturer", rezource.getManufacturer());
    params.addValue("model", rezource.getModel());
    params.addValue("keywords", rezource.getKeywords());
    params.addValue("addr1", rezource.getAddr1());
    params.addValue("addr2", rezource.getAddr2());
    params.addValue("city", rezource.getCity());
    params.addValue("state", rezource.getState());
    params.addValue("province", rezource.getProvince());
    params.addValue("postalCode", rezource.getPostalCode());
    params.addValue("country", rezource.getCountry());
    params.addValue("billing", rezource.getBilling());
    log.info("RezourceDao.insertRezource: sql = {}, parameters = {}", sql, params);
    jdbcTemplate.update(sql, params, keyHolder);
    
    return keyHolder.getKey().longValue();
  }

}
