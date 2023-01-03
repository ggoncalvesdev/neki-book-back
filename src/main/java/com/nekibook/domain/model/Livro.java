package com.nekibook.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "idLivro"
)
@Entity
@Table(name = "livro")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "idlivro")
  private Integer idLivro;

  @Column(name = "nomelivro")
  private String nomeLivro;

  @Column(name = "autor")
  private String autor;

  @Column(name = "imagem")
  private String imagem;

  @OneToMany(mappedBy = "livros")
  private Set<Leitura> leituras;

  public Set<Leitura> getLeituras() {
    return leituras;
  }

  public void setLeituras(Set<Leitura> leituras) {
    this.leituras = leituras;
  }

  public String getAutor() {
    return autor;
  }

  public void setAutor(String autor) {
    this.autor = autor;
  }

  public Integer getIdLivro() {
    return idLivro;
  }

  public void setIdLivro(Integer idLivro) {
    this.idLivro = idLivro;
  }

  public String getNomeLivro() {
    return nomeLivro;
  }

  public void setNomeLivro(String nomeLivro) {
    this.nomeLivro = nomeLivro;
  }

  public String getImagem() {
    return imagem;
  }

  public void setImagem(String imagem) {
    this.imagem = imagem;
  }
}
