package com.nekibook.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.nekibook.api.controller.CommentController;
import com.nekibook.api.controller.SummaryController;
import com.nekibook.api.controller.UsuarioController;
import com.nekibook.api.model.CommentModel;
import com.nekibook.domain.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CommentModelAssembler
  extends RepresentationModelAssemblerSupport<Comment, CommentModel> {

  @Autowired
  private ModelMapper modelMapper;

  public CommentModelAssembler() {
    super(CommentController.class, CommentModel.class);
  }

  @Override
  public CommentModel toModel(Comment comment) {
    CommentModel commentModel = createModelWithId(
      comment.getIdComment(),
      comment
    );
    modelMapper.map(comment, commentModel);

    commentModel.add(linkTo(UsuarioController.class).withRel("usuario"));

    commentModel.add(linkTo(SummaryController.class).withRel("sumarry"));

    return commentModel;
  }

  @Override
  public CollectionModel<CommentModel> toCollectionModel(
    Iterable<? extends Comment> entities
  ) {
    return super
      .toCollectionModel(entities)
      .add(linkTo(CommentController.class).withSelfRel());
  }
}
