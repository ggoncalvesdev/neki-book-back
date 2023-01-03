package com.nekibook.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "idAmizade"
    )
@Entity
@Table(name = "amizade")
public class Amizade {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_amizade")
	private int idAmizade;

    @ManyToOne
    @JoinColumn (name = "user_seguidor", referencedColumnName = "user_id")
    private Usuario usuarioSeguidor;

    @ManyToOne
    @JoinColumn (name = "user_seguido", referencedColumnName = "user_id")
    private Usuario usuarioSeguido;

    public int getIdAmizade() {
        return idAmizade;
    }

    public void setIdAmizade(int idAmizade) {
        this.idAmizade = idAmizade;
    }

    public Usuario getUsuarioSeguidor() {
        return usuarioSeguidor;
    }

    public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
        this.usuarioSeguidor = usuarioSeguidor;
    }

    public Usuario getUsuarioSeguido() {
        return usuarioSeguido;
    }

    public void setUsuarioSeguido(Usuario usuarioSeguido) {
        this.usuarioSeguido = usuarioSeguido;
    }
}
