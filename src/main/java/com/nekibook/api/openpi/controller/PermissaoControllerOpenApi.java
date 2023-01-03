package com.nekibook.api.openpi.controller;

import com.nekibook.api.model.PermissaoModel;
import org.springframework.hateoas.CollectionModel;

public interface PermissaoControllerOpenApi {
  CollectionModel<PermissaoModel> listar();
}
