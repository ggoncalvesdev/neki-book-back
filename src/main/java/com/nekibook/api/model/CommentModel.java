package com.nekibook.api.model;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "comments")
@Setter
@Getter
public class CommentModel extends RepresentationModel<CommentModel> {

  private int idComment;

  private String comment;

  private OffsetDateTime dataCriacao;

  private UsuarioResumoModel usuarios;

  private SummaryResumoModel summaries;
}
