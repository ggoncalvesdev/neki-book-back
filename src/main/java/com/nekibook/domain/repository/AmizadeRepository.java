package com.nekibook.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekibook.domain.model.Amizade;
import com.nekibook.domain.model.Usuario;

public interface AmizadeRepository extends JpaRepository<Amizade, Integer> {
    public List<Amizade> findByUsuarioSeguidor (Usuario usuarioSeguidor);
    public List<Amizade> findByUsuarioSeguido (Usuario id_usuarioSeguido);
    public Amizade findByUsuarioSeguidorAndUsuarioSeguido (Usuario id_usuarioSeguidor, Usuario id_usuarioSeguido);
}
