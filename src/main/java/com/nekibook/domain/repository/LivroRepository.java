package com.nekibook.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekibook.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro,Integer>{
    List<Livro> findAllByNomeLivroContainingIgnoreCase(String nomelivro);
}
