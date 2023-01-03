package com.nekibook.api.assembler;

import com.nekibook.api.model.PermissaoModel;
import com.nekibook.api.openpi.NekiLink;
import com.nekibook.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PermissaoModelAssembler
  implements RepresentationModelAssembler<Permissao, PermissaoModel> {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private NekiLink nekiLinks;

  @Override
  public PermissaoModel toModel(Permissao permissao) {
    PermissaoModel permissaoModel = modelMapper.map(
      permissao,
      PermissaoModel.class
    );
    return permissaoModel;
  }

  @Override
  public CollectionModel<PermissaoModel> toCollectionModel(
    Iterable<? extends Permissao> entities
  ) {
    return RepresentationModelAssembler.super
      .toCollectionModel(entities)
      .add(nekiLinks.linkToPermissoes());
  }
}
