package com.nekibook.api.model;

import com.nekibook.domain.model.Summary;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "leitura")
@Setter
@Getter
public class LeituraModel extends RepresentationModel<LeituraModel> {

  private int idLeitura;

  private UsuarioModel usuarios;

  private LivroModel livros;
  private Set<Summary> summaries;
}
