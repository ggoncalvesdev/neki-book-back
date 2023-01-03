package com.nekibook.api.model;

import com.nekibook.domain.model.Amizade;
import com.nekibook.domain.model.Comment;
import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Summary;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "usuario")
@Setter
@Getter
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

  private Integer id;

  private String nome;

  private String email;

  private String setor;

  private String imagemNome;

  private String imagemFileName;

  private String imagemUrl;

  private Set<Leitura> leituras;

  private Set<Summary> summaries;

  private Set<Comment> comments;

  private Set<Amizade> amizadeSeguidores;

  private Set<Amizade> amizadeSeguidos;
}
