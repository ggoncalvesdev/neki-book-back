package com.nekibook.domain.repository;

import com.nekibook.domain.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
  Optional<Usuario> findByEmail(String email);
  List<Usuario> findAllByNomeContainingIgnoreCase(String nomeUsuario);
}
