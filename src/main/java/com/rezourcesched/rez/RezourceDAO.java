package com.rezourcesched.rez;

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
  public List<Rezource> fetchRezource(Long rezourceId, String name, RezourceType rezourceType) {
    log.info("RezourceDAO.fetchRezource rezourceId = {}, name = {}, rezourceType = {}", rezourceId, name, rezourceType);
    
    String sql = "SELECT * FROM rezource WHERE 1=1";  //where 1=1 allows me to construct additional optional bind variables
    Map<String, Object> params = new HashMap<>();
    if (!(name == null)) {  //if name is not null
      sql += " and name = :name";
      params.put("name", name);
    }
    if (!(rezourceType == null)) {  //if type is not null
      sql += " and rezource_type = :rezourceType";
      params.put("rezourceType", rezourceType);
    }
    if (!(rezourceId == null)) {
      sql += " and rezourcer_id = :rezourcerId";
      params.put("rezourcerId", rezourceId.toString());
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
//            .keywords(rs.getString("key_words"))
//            .startTime(rs.getTimestamp("start_time"))
//            .endTime(rs.getTimestamp("end_time"))
//            .regularRate(rs.getDouble("regular_rate"))
//            .weekendRate(rs.getDouble("weekend_rate"))
//            .deposit(rs.getDouble("deposit"))
//            .cleaningFee(rs.getDouble("cleaning_fee"))
//            .travelRate(rs.getDouble("travel_rate"))
//            .emergRate(rs.getDouble("emerg_rate"))
//            .sizeInFeet(rs.getInt("size_in_ft"))
//            .people(rs.getInt("people"))
//            .year(rs.getInt("year"))
//            .bedrooms(rs.getInt("bedrooms"))
//            .bathrooms(rs.getInt("bathrooms"))
//            .beds(rs.getInt("beds"))
            .rezourceName(rs.getString("name"))
            .rezourceDescription(rs.getString("description"))
//            .style(rs.getString("style"))
//            .manufacturer(rs.getString("manufacturer"))
//            .model(rs.getString("model"))
            .build();
        // @formatter:on
      }
    });
  }

}
