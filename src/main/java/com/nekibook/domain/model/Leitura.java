package com.nekibook.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "idLeitura"
    )
@Entity
@Table(name = "leitura")
public class Leitura {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_leitura")
	private int idLeitura;

    @ManyToOne
    @JoinColumn (name = "user_id", referencedColumnName = "user_id")
    private Usuario usuarios;

    @ManyToOne
    @JoinColumn (name = "idlivro", referencedColumnName = "idlivro")
    private Livro livros;

    @Enumerated(EnumType.STRING)
	private StatusLeitura status; //= StatusLeitura.DESEJADO;

    @OneToMany (mappedBy = "leituras")
    private Set<Summary> summaries;

    public Set<Summary> getSummaries() {
        return summaries;
    }

    public void setSummaries(Set<Summary> summaries) {
        this.summaries = summaries;
    }

    public StatusLeitura getStatus() {
        return status;
    }

    public void setStatus(StatusLeitura status) {
        this.status = status;
    }

    public int getIdLeitura() {
        return idLeitura;
    }

    public void setIdLeitura(int idLeitura) {
        this.idLeitura = idLeitura;
    }

    public Usuario getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }

    public Livro getLivros() {
        return livros;
    }

    public void setLivros(Livro livros) {
        this.livros = livros;
    }
}
