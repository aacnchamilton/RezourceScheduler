package com.rezourcesched.rez;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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
import com.rezourcesched.rez.entity.Rezource;
import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {
    "classpath:resource_scheduler_schema.sql",
    "classpath:resource_scheduler_data.sql"},
    config = @SqlConfig(encoding = "utf-8"))
class FetchResourceTest {
  @Autowired
  @Getter
  private TestRestTemplate testRestTemplate;
  
  @LocalServerPort
  private int serverPort;
  
  @Test
  void testThatRezourceIsReturnedWhenAValidIdIsSupplied() {
    //Given a valid Rezource Id and URI
    int rezourceId = 1;
    String uri = String.format("http://localhost:%d/rezource?rezourceId=%d", serverPort, rezourceId);
    
    System.out.println("FetchResourceTest: By Id: URI:" + uri);
    
    //When a connection is made to the URI
    ResponseEntity<List<Rezource>> response = testRestTemplate.exchange(uri, HttpMethod.GET,null, new ParameterizedTypeReference<>() {});
    
    //Then a status code 200 is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

}
