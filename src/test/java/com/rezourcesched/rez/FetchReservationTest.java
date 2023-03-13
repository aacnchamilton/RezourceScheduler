package com.rezourcesched.rez;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.rezourcesched.rez.entity.Reservation;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resource_scheduler_schema.sql",
    "classpath:resource_scheduler_data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
public class FetchReservationTest {
  @Autowired
  @Getter
  private TestRestTemplate testRestTemplate;
  
  @LocalServerPort
  private int serverPort;
  
  @Test
  void testThatReservationIsReturnedWhenAValidIdIsSupplied() {
    //Given a valid Person Id and URI
    int reservationId = 1;
    String uri = String.format("http://localhost:%d/reservation?reservationId=%d", serverPort, reservationId);
    
    System.out.println("FetchReservationTest: By Id: URI:" + uri);
    
    //When a connection is made to the URI
    ResponseEntity<List<Reservation>> response = testRestTemplate.exchange(uri, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});
    
    //Then a status code 200 is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
