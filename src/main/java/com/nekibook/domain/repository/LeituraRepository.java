package com.nekibook.domain.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.Usuario;

public interface LeituraRepository extends JpaRepository<Leitura, Integer> {
	public List<Leitura> findByUsuarios (Usuario usuarios);
	public List<Leitura> findByLivros (Livro livros);
	public Leitura findByLivrosAndUsuarios (Livro idlivro, Usuario user_id);
    
}
