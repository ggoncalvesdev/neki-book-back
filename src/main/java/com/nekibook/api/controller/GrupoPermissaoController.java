package com.nekibook.api.controller;

import com.nekibook.api.assembler.PermissaoModelAssembler;
import com.nekibook.api.model.PermissaoModel;
import com.nekibook.api.openpi.NekiLink;
import com.nekibook.api.openpi.controller.GrupoPermissaoControllerOpenApi;
import com.nekibook.domain.model.Grupo;
import com.nekibook.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController
  implements GrupoPermissaoControllerOpenApi {

  @Autowired
  private GrupoService cadastroGrupo;

  @Autowired
  private PermissaoModelAssembler permissaoModelAssembler;

  @Autowired
  private NekiLink nekiLinks;

  @Override
  @GetMapping
  public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
    Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

    CollectionModel<PermissaoModel> permissoesModel = permissaoModelAssembler
      .toCollectionModel(grupo.getPermissoes())
      .removeLinks()
      .add(nekiLinks.linkToGrupoPermissoes(grupoId))
      .add(nekiLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

    permissoesModel
      .getContent()
      .forEach(permissaoModel -> {
        permissaoModel.add(
          nekiLinks.linkToGrupoPermissaoDesassociacao(
            grupoId,
            permissaoModel.getId(),
            "desassociar"
          )
        );
      });

    return permissoesModel;
  }

  @DeleteMapping("/{permissaoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String desassociar(
    @PathVariable Long grupoId,
    @PathVariable Long permissaoId
  ) {
    cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
    return null;
  }

  @PutMapping("/{permissaoId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public String associar(
    @PathVariable Long grupoId,
    @PathVariable Long permissaoId
  ) {
    cadastroGrupo.associarPermissao(grupoId, permissaoId);
    return null;
  }
}
