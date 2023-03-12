package com.rezourcesched.rez;

import java.util.List;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;

public interface iRezourceDAO {
  List<Rezource> fetchRezource(Long rezourceId, String state, String postalCode, RezourceType rezourceType, Long rezourcerId);
}
