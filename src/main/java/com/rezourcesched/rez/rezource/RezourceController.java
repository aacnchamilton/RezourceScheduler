package com.rezourcesched.rez.rezource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RezourceController implements iRezourceController {
  
  @Autowired
  RezourceService rezourceService;

  @Override
  public List<Rezource> fetchRezource(Long rezourceId, String state, String postalCode, RezourceType rezourceType, Long rezourcerId) {
    log.info("RezourceController.fetchRezource: rezourceId = {}, state = {}, postalCode = {}, rezourceType = {}, rezourcerId = {}"
        , rezourceId, state, postalCode, rezourceType, rezourcerId);
    return rezourceService.fetchRezource(rezourceId, state, postalCode, rezourceType, rezourcerId);
  }

  @Override
  public Rezource createRezource(Rezource rezource) {
    log.info("RezourceController.createRezource: rezource = {}", rezource);
    return rezourceService.createRezource(rezource);
  }

  @Override
  public Rezource reviseRezource(Rezource rezource) {
    log.info("RezourceController.reviseRezource: rezource = {}",rezource);
    return rezourceService.reviseRezource(rezource);
  }
}
