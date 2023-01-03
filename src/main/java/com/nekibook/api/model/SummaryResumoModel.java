package com.nekibook.api.model;

import com.nekibook.domain.model.Comment;
import com.nekibook.domain.model.Leitura;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "summary")
@Setter
@Getter
public class SummaryResumoModel
  extends RepresentationModel<SummaryResumoModel> {

  private int idSummary;

  private String summary;

  private UsuarioModel usuarios;

  /*   private LeituraModel leituras; */

  private Set<Leitura> leituras;
}
