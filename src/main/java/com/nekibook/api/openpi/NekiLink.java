package com.nekibook.api.openpi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.nekibook.api.controller.GrupoPermissaoController;
import com.nekibook.api.controller.PermissaoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class NekiLink {

  public Link linkToPermissoes(String rel) {
    return linkTo(PermissaoController.class).withRel(rel);
  }

  public Link linkToPermissoes() {
    return linkToPermissoes(IanaLinkRelations.SELF.value());
  }

  public Link linkToGrupoPermissoes(Long grupoId, String rel) {
    return linkTo(methodOn(GrupoPermissaoController.class).listar(grupoId))
      .withRel(rel);
  }

  public Link linkToGrupoPermissoes(Long grupoId) {
    return linkToGrupoPermissoes(grupoId, IanaLinkRelations.SELF.value());
  }

  public Link linkToGrupoPermissaoAssociacao(Long grupoId, String rel) {
    return linkTo(
      methodOn(GrupoPermissaoController.class).associar(grupoId, null)
    )
      .withRel(rel);
  }

  public Link linkToGrupoPermissaoDesassociacao(
    Long grupoId,
    Long permissaoId,
    String rel
  ) {
    return linkTo(
      methodOn(GrupoPermissaoController.class).desassociar(grupoId, permissaoId)
    )
      .withRel(rel);
  }
}
