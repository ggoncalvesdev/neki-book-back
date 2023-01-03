package com.nekibook.api.assembler;

import com.nekibook.api.model.GrupoModel;
import com.nekibook.domain.model.Grupo;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupoModelAssembler {

  @Autowired
  private ModelMapper modelMapper;

  public GrupoModel toModel(Grupo grupo) {
    return modelMapper.map(grupo, GrupoModel.class);
  }

  public List<GrupoModel> toCollectionModel(Collection<Grupo> grupos) {
    return grupos
      .stream()
      .map(grupo -> toModel(grupo))
      .collect(Collectors.toList());
  }
}
