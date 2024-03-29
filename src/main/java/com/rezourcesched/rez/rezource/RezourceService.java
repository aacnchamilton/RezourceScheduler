package com.rezourcesched.rez.rezource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RezourceService implements iRezourceService {
  
  @Autowired
  RezourceDAO rezourceDao;

  @Override
  public List<Rezource> fetchRezource(Long rezourceId, String state, String postalCode, RezourceType rezourceType, Long rezourcerId) {
    log.info("RezourceService: fetchRezource: rezourceId = {}, state = {}, postalCode = {}, rezourceType = {}, rezourcerId = {}"
        , rezourceId, state, postalCode, rezourceType, rezourcerId);
    return rezourceDao.fetchRezource(rezourceId, state, postalCode, rezourceType, rezourcerId);
  }

  @Override
  public Rezource createRezource(Rezource rezource) {
    log.info("RezourceService: createRezource: rezource = {}", rezource);
    return rezourceDao.createRezource(rezource);
  }

  @Override
  public Rezource reviseRezource(Rezource rezource) {
    log.info("RezourceService: reviseRezource: rezource = {}",rezource);
    return rezourceDao.reviseRezource(rezource);
  }

}
