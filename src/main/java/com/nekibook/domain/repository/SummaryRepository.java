package com.nekibook.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.model.Usuario;

public interface SummaryRepository extends JpaRepository<Summary, Integer> {
	public List<Summary> findByUsuarios (Usuario usuarios);
	public List<Summary> findByLeituras (Leitura id_leitura);
	public Summary findByLeiturasAndUsuarios (Leitura id_leitura, Usuario user_id);
	
    
}