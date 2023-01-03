package com.nekibook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

@JsonIgnoreProperties
@Entity
@Table(name = "comment")
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_comment")
  private int idComment;

  @Column(name = "comment")
  private String comment;

  @CreationTimestamp
  private OffsetDateTime dataCriacao;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private Usuario usuarios;

  @ManyToOne
  @JoinColumn(name = "id_summary", referencedColumnName = "id_summary")
  private Summary summaries;

  public int getIdComment() {
    return idComment;
  }

  public void setIdComment(int idComment) {
    this.idComment = idComment;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Usuario getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(Usuario usuarios) {
    this.usuarios = usuarios;
  }

  public Summary getSummaries() {
    return summaries;
  }

  public void setSummaries(Summary summaries) {
    this.summaries = summaries;
  }
}
