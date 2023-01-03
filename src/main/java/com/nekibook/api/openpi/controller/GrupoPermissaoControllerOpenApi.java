package com.nekibook.api.openpi.controller;

import com.nekibook.api.model.PermissaoModel;
import org.springframework.hateoas.CollectionModel;

public interface GrupoPermissaoControllerOpenApi {
  CollectionModel<PermissaoModel> listar(Long grupoId);
  /*   ResponseEntity<Void> desassociar(Long grupoId, Long permissaoId); */

  /*   ResponseEntity<Void> associar(Long grupoId, Long permissaoId); */
}
