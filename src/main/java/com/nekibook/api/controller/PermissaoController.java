package com.nekibook.api.controller;

import com.nekibook.api.assembler.PermissaoModelAssembler;
import com.nekibook.api.model.PermissaoModel;
import com.nekibook.api.openpi.controller.PermissaoControllerOpenApi;
import com.nekibook.domain.model.Permissao;
import com.nekibook.domain.repository.PermissaoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
  path = "/permissoes",
  produces = MediaType.APPLICATION_JSON_VALUE
)
public class PermissaoController implements PermissaoControllerOpenApi {

  @Autowired
  private PermissaoRepository permissaoRepository;

  @Autowired
  private PermissaoModelAssembler permissaoModelAssembler;

  @Override
  @GetMapping
  public CollectionModel<PermissaoModel> listar() {
    List<Permissao> todasPermissoes = permissaoRepository.findAll();

    return permissaoModelAssembler.toCollectionModel(todasPermissoes);
  }
}
