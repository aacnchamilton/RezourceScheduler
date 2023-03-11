package com.rezourcesched.rez;

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
  public List<Rezource> fetchRezource(Long rezourceId, String name, RezourceType rezourceType, Long rezourcerId) {
    log.info("RezourceController.fetchRezource: rezourceId = {}, name = {}, rezourceType = {}, rezourcerId = {}", rezourceId, name, rezourceType, rezourcerId);
    return rezourceService.fetchRezource(rezourceId, name, rezourceType, rezourcerId);
  }
}
