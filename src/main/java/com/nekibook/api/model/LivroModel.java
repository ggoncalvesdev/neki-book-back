package com.nekibook.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "livro")
@Setter
@Getter
public class LivroModel extends RepresentationModel<LivroModel> {

  private Integer idLivro;

  private String nomeLivro;
  /*   private String autor;

  private String imagem;

  private Set<Leitura> leituras; */
}
