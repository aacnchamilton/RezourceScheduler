package com.rezourcesched.rez.rezource;

import java.util.List;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;

public interface iRezourceService {
  
  List<Rezource> fetchRezource(Long rezourceId, String state, String postalCode, RezourceType rezourceType, Long rezourcerId);
  
  Rezource createRezource(Rezource rezource);
  
  Rezource reviseRezource(Rezource rezource);
}
