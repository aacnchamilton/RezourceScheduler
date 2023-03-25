package com.rezourcesched.rez.reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
    return selectReservation(reservationId, rezourceId, schedulerId, dateFrom, dateTo);
  }
  
  @Override
  public Reservation createReservation(Reservation reservation) {
    log.info("ReservationDao.createReservation: reservation = {}", reservation);
    Long reservationId = insertReservation(reservation);
    log.info("ReservationDao.createReservation: Newly Created reservationId: {}", reservationId);
    List<Reservation> reservationCreated = fetchReservation(reservationId, null, null, null, null);
    return reservationCreated.get(0);
  }
  
  private Long insertReservation(Reservation reservation) {
    String sql = "INSERT INTO reservation ("
        + "date_from, date_to, start_time, end_time, travel_time, rezource_id, scheduler_id, "
        + "num_of_people, special_requests, status)"
        + " VALUES ("
        + ":dateFrom, :dateTo, :startTime, :endTime, :travelTime, :rezourceId, :schedulerId, "
        + ":numOfPeople, :specialRequests, :status)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("dateFrom", reservation.getDateFrom());
    params.addValue("dateTo", reservation.getDateTo());
    params.addValue("startTime", reservation.getStartTime());
    params.addValue("endTime", reservation.getEndTime());
    params.addValue("travelTime", reservation.getTravelTime());
    params.addValue("rezourceId", reservation.getRezourceId());
    params.addValue("schedulerId", reservation.getSchedulerId());
    params.addValue("numOfPeople", reservation.getNumOfPeople());
    params.addValue("specialRequests", reservation.getSpecialRequests());
    params.addValue("status", reservation.getStatus().toString());
    log.info("PersonDao.insertPerson: sql = {}, parameters = {}", sql, params);
    jdbcTemplate.update(sql, params, keyHolder);
    
    return keyHolder.getKey().longValue();
  }

  private List<Reservation> selectReservation(Long reservationId, Long rezourceId, Long schedulerId, Date dateFrom,
      Date dateTo) {
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
