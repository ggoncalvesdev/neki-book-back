package com.nekibook.domain.filter;

import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Setter
@Getter
public class CommentFilter {

  private Integer commentId;
  private Integer usuarioId;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private OffsetDateTime dataCriacaoInicio;
}
