package com.nekibook.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "idSummary"
)
@Entity
@Table(name = "summary")
public class Summary {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_summary")
  private int idSummary;

  @Column(name = "summary")
  private String summary;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private Usuario usuarios;

  @ManyToOne
  @JoinColumn(name = "id_leitura", referencedColumnName = "id_leitura")
  private Leitura leituras;

  @OneToMany(mappedBy = "summaries")
  private Set<Comment> comments;

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public int getIdSummary() {
    return idSummary;
  }

  public void setIdSummary(int idSummary) {
    this.idSummary = idSummary;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public Usuario getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(Usuario usuarios) {
    this.usuarios = usuarios;
  }

  public Leitura getLeituras() {
    return leituras;
  }

  public void setLeituras(Leitura leituras) {
    this.leituras = leituras;
  }
}
