package com.rezourcesched.rez;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RezourceController implements iRezourceController {

  @Override
  public List<Rezource> fetchRezource(Long rezourceId, String name, RezourceType rezourceType) {
    return null;
  }

}
