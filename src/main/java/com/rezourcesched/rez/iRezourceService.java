package com.rezourcesched.rez;

import java.util.List;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;

public interface iRezourceService {
  List<Rezource> fetchRezource(Long rezourceId, String name, RezourceType rezourceType);
}
