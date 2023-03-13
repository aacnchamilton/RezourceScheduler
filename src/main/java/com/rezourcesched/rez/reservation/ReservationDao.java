package com.rezourcesched.rez.reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.rezourcesched.rez.entity.Person;
import com.rezourcesched.rez.entity.Reservation;
import com.rezourcesched.rez.entity.ReservationStatus;
import com.rezourcesched.rez.entity.Status;
import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j
public class ReservationDao implements iReservationDao {
  
  @Autowired
  NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Reservation> fetchReservation(Long reservationId, Long rezourceId, Long schedulerId, Date dateFrom,
      Date dateTo) {
    log.info("ReservationDao.fetchReservation: reservationId = {}, resourceId = {}, schedulerId = {}, dateFrom = {}, dateTo{}"
        , reservationId, rezourceId, schedulerId, dateFrom, dateTo);
    
    String sql = "SELECT * FROM reservation WHERE 1=1";  //where 1=1 allows me to construct additional optional bind variables
    Map<String, Object> params = new HashMap<>();
    if (!(rezourceId == null)) {
      sql += " and rezource_id = :rezourceId";
      params.put("rezourceId", rezourceId.toString());
    }
    if (!(schedulerId == null)) {  //if name is not null
      sql += " and scheduler_id = :schedulerId";
      params.put("schedulerId", schedulerId.toString());
    }
    if (!(dateFrom == null)) {  //if name is not null
      sql += " and date_from >= :dateFrom";
      params.put("dateFrom", dateFrom);  //Need to convert Date to String
    }
    if (!(dateTo == null)) {  //if name is not null
      sql += " and date_to <= :dateTo";
      params.put("dateTo", dateTo);  //Need to convert Date to String
    }
    if (!(reservationId == null)) {  //if name is not null
      sql += " and reservation_id = :reservationId";
      params.put("reservationId", reservationId);
    }
    log.info("ReservationDao.fetchPerson: sql = {}, params = {}", sql, params);
    
    return jdbcTemplate.query(sql, params, new RowMapper<>() {
      
      @Override
      public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        // @formatter:off
        return Reservation.builder()
            .reservationId(rs.getLong("reservation_id"))
            .rezourceId(rs.getLong("rezource_Id"))
            .schedulerId(rs.getLong("scheduler_id"))
            .dateFrom(rs.getDate("date_from"))
            .dateTo(rs.getDate("date_to"))
            .startTime(rs.getTime("start_time"))
            .endTime(rs.getTime("end_time"))
            .travelTime(rs.getInt("travel_time"))
            .numOfPeople(rs.getInt("num_of_people"))
            .specialRequests(rs.getString("special_requests"))
            .status(ReservationStatus.valueOf(rs.getString("status")))
            .build();
        // @formatter:on
      }
    });
  }

}
