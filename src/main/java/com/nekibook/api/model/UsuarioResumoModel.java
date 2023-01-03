package com.nekibook.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuario")
@Setter
@Getter
public class UsuarioResumoModel
  extends RepresentationModel<UsuarioResumoModel> {

  private Integer id;

  private String nome;

  private String setor;

  private String imagemNome;

  private String imagemFileName;

  private String imagemUrl;
  /*   private Set<Leitura> leituras;

  private Set<Summary> summaries;

  private Set<Comment> comments;

  private Set<Amizade> amizadeSeguidores;

  private Set<Amizade> amizadeSeguidos; */
}
