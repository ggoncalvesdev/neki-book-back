package com.nekibook.domain.service;

import com.nekibook.domain.model.Comment;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.repository.CommentRepository;
import com.nekibook.domain.repository.SummaryRepository;
import com.nekibook.domain.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  CommentRepository commentRepository;

  @Autowired
  UsuarioRepository userRepository;

  @Autowired
  SummaryRepository summaryRepository;

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  @Transactional
  public List<Comment> findAllBySummary(Summary id_summary) {
    List<Comment> comments = new ArrayList<>();
    comments = commentRepository.findBySummaries(id_summary);
    return comments;
  }

  public Comment getCommentById(Integer id) {
    return commentRepository.findById(id).orElse(null);
  }

  public Comment saveComment(Comment comment) {
    return commentRepository.save(comment);
  }

  public Comment updateComment(Comment comment, Integer id) {
    Comment commentExistenteNoBanco = getCommentById(id);

    commentExistenteNoBanco.setComment(comment.getComment());
    commentExistenteNoBanco.setSummaries(comment.getSummaries());
    commentExistenteNoBanco.setUsuarios(comment.getUsuarios());

    return commentRepository.save(commentExistenteNoBanco);
  }

  public Comment deleteComment(Integer id) {
    commentRepository.deleteById(id);
    return getCommentById(id);
  }
}
