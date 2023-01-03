package com.nekibook.domain.repository.spec;

import com.nekibook.domain.filter.CommentFilter;
import com.nekibook.domain.model.Comment;
import java.util.ArrayList;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecs {

  public static Specification<Comment> usandoFiltro(CommentFilter filtro) {
    return (root, query, builder) -> {
      if (Comment.class.equals(query.getResultType())) {
        root.fetch("usuarios").fetch("comments");
      }

      var predicates = new ArrayList<Predicate>();

      if (filtro.getUsuarioId() != null) {
        predicates.add(
          builder.equal(root.get("usuarios"), filtro.getUsuarioId())
        );
      }

      if (filtro.getCommentId() != null) {
        predicates.add(
          builder.equal(root.get("comments"), filtro.getCommentId())
        );
      }

      if (filtro.getDataCriacaoInicio() != null) {
        predicates.add(
          builder.greaterThanOrEqualTo(
            root.get("dataCriacao"),
            filtro.getDataCriacaoInicio()
          )
        );
      }

      return builder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
