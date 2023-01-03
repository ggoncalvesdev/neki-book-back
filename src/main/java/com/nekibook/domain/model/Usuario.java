package com.nekibook.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class,
  property = "id"
)
@Entity
@Table(name = "usuario")
public class Usuario {

  @EqualsAndHashCode.Include
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "user_nome")
  private String nome;

  @Column(name = "user_email")
  private String email;

  @Column(name = "setor")
  private String setor;

  @Column(name = "imagem_nome")
  private String imagemNome;

  @Column(name = "imagem_filename")
  private String imagemFileName;

  @Column(name = "imagem_url")
  private String imagemUrl;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @Column(name = "user_password")
  private String password;

  @OneToMany(mappedBy = "usuarios")
  private Set<Leitura> leituras;

  @OneToMany(mappedBy = "usuarios")
  private Set<Summary> summaries;

  @OneToMany(mappedBy = "usuarios")
  private Set<Comment> comments;

  @OneToMany(mappedBy = "usuarioSeguidor")
  private Set<Amizade> amizadeSeguidores;

  @OneToMany(mappedBy = "usuarioSeguido")
  private Set<Amizade> amizadeSeguidos;

  public Set<Amizade> getAmizadeSeguidores() {
    return amizadeSeguidores;
  }

  public void setAmizadeSeguidores(Set<Amizade> amizadeSeguidores) {
    this.amizadeSeguidores = amizadeSeguidores;
  }

  public Set<Amizade> getAmizadeSeguidos() {
    return amizadeSeguidos;
  }

  public void setAmizadeSeguidos(Set<Amizade> amizadeSeguidos) {
    this.amizadeSeguidos = amizadeSeguidos;
  }

  public Set<Summary> getSummaries() {
    return summaries;
  }

  public void setSummaries(Set<Summary> summaries) {
    this.summaries = summaries;
  }

  public Set<Comment> getComments() {
    return comments;
  }

  public void setComments(Set<Comment> comments) {
    this.comments = comments;
  }

  public Set<Leitura> getLeituras() {
    return leituras;
  }

  public void setLeituras(Set<Leitura> leituras) {
    this.leituras = leituras;
  }

  /*   @CreationTimestamp
  @Column(nullable = false, columnDefinition = "")
  private OffsetDateTime dataCadastro; */

  @ManyToMany
  @JoinTable(
    name = "usuario_grupo",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "grupo_id")
  )
  private Set<Grupo> grupos = new HashSet<>();

  public boolean senhaCoincideCom(String senha) {
    return getPassword().equals(senha);
  }

  public boolean senhaNaoCoincideCom(String senha) {
    return !senhaCoincideCom(senha);
  }

  public boolean removerGrupo(Grupo grupo) {
    return getGrupos().remove(grupo);
  }

  public boolean adicionarGrupo(Grupo grupo) {
    return getGrupos().add(grupo);
  }

  public String getImagemNome() {
    return imagemNome;
  }

  public void setImagemNome(String imagemNome) {
    this.imagemNome = imagemNome;
  }

  public String getImagemFileName() {
    return imagemFileName;
  }

  public void setImagemFileName(String imagemFileName) {
    this.imagemFileName = imagemFileName;
  }

  public String getImagemUrl() {
    return imagemUrl;
  }

  public void setImagemUrl(String imagemUrl) {
    this.imagemUrl = imagemUrl;
  }
}
