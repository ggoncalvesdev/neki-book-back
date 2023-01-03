package com.nekibook.domain.repository;

import com.nekibook.domain.model.Comment;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository
  extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
  public List<Comment> findByUsuarios(Usuario usuarios);

  /* @Query("from Comment c join fetch c.usuario join fetch c.summary") */
  List<Comment> findAll();

  public List<Comment> findBySummaries(Summary idSummary);
}
