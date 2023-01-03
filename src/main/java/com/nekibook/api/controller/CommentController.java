package com.nekibook.api.controller;

import com.nekibook.api.assembler.CommentModelAssembler;
import com.nekibook.api.model.CommentModel;
import com.nekibook.core.data.PageableTranslator;
import com.nekibook.domain.model.Comment;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.repository.CommentRepository;
import com.nekibook.domain.service.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

  @Autowired
  CommentService commentService;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private CommentModelAssembler commentModelAssembler;

  /*   @Autowired
  private PagedResourcesAssembler<Comment> pagedResourcesAssembler; */

  @GetMapping
  public CollectionModel<CommentModel> listar() {
    List<Comment> todosCommentarios = commentRepository.findAll();

    return commentModelAssembler.toCollectionModel(todosCommentarios);
  }

  /*  @GetMapping
  public ResponseEntity<List<Comment>> getAllComments() {
    return new ResponseEntity<>(commentService.getAllComments(), HttpStatus.OK);
  } */

  /* @GetMapping("/pageable")
  public PagedModel<CommentModel> pesquisar(
    CommentFilter filtro,
    @PageableDefault(size = 10) Pageable pageable
  ) {
    Pageable pageableTraduzido = traduzirPageable(pageable);

    Page<Comment> commentsPage = commentRepository.findAll(
      CommentSpecs.usandoFiltro(filtro),
      pageableTraduzido
    );

    commentsPage = new PageWrapper<>(commentsPage, pageable);

    return pagedResourcesAssembler.toModel(
      commentsPage,
      commentResumoModelAssembler
    );
  } */

  @GetMapping("/summary/{id_summary}")
  public ResponseEntity<List<Comment>> getBySummary(
    @PathVariable Summary id_summary
  ) {
    return new ResponseEntity<>(
      commentService.findAllBySummary(id_summary),
      HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
    Comment comment = commentService.getCommentById(id);
    if (null != comment) return new ResponseEntity<>(
      comment,
      HttpStatus.OK
    ); else return new ResponseEntity<>(comment, HttpStatus.NOT_FOUND);
  }

  @PostMapping
  public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
    return new ResponseEntity<>(
      commentService.saveComment(comment),
      HttpStatus.CREATED
    );
  }

  @PutMapping("/{id}")
  public ResponseEntity<Comment> updateComment(
    @RequestBody Comment comment,
    @PathVariable Integer id
  ) {
    return new ResponseEntity<>(
      commentService.updateComment(comment, id),
      HttpStatus.OK
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Comment> deleteComment(@PathVariable Integer id) {
    Comment comment = commentService.getCommentById(id);
    if (null == comment) return new ResponseEntity<>(
      comment,
      HttpStatus.NOT_FOUND
    ); else return new ResponseEntity<>(
      commentService.deleteComment(id),
      HttpStatus.OK
    );
  }

  private Pageable traduzirPageable(Pageable apiPageable) {
    var mapeamento = Map.of(
      "idComment",
      "idComment",
      "comments",
      "comments"
      /*  "dataCriacao",
      "dataCriacao",
      "usuario.nome",
      "usuario.nome",
      "usuario.id",
      "usuario.id",
      "summaries.idSummary",
      "summaries.idSummary" */
    );

    return PageableTranslator.translate(apiPageable, mapeamento);
  }
}
