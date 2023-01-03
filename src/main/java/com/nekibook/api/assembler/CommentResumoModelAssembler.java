package com.nekibook.api.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.nekibook.api.controller.CommentController;
import com.nekibook.api.controller.SummaryController;
import com.nekibook.api.controller.UsuarioController;
import com.nekibook.api.model.CommentModel;
import com.nekibook.domain.model.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CommentResumoModelAssembler
  extends RepresentationModelAssemblerSupport<Comment, CommentModel> {

  @Autowired
  private ModelMapper modelMapper;

  public CommentResumoModelAssembler() {
    super(CommentController.class, CommentModel.class);
  }

  @Override
  public CommentModel toModel(Comment comment) {
    CommentModel commentModel = createModelWithId(
      comment.getIdComment(),
      comment
    );
    modelMapper.map(comment, commentModel);

    commentModel.add(linkTo(CommentController.class).withRel("comments"));

    commentModel
      .getUsuarios()
      .add(
        linkTo(
          methodOn(UsuarioController.class)
            .getById(comment.getUsuarios().getId())
        )
          .withSelfRel()
      );

    commentModel
      .getSummaries()
      .add(
        linkTo(methodOn(SummaryController.class).getAllSummaries())
          .withSelfRel()
      );

    return commentModel;
  }
}
