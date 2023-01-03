package com.nekibook.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekibook.domain.model.Amizade;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.repository.AmizadeRepository;

@Service
public class AmizadeService {

    @Autowired
	AmizadeRepository amizadeRepository;

    public List<Amizade> getAllAmizades(){
		return amizadeRepository.findAll();
	}
    
    public Amizade getAmizadeById(Integer id) {
		//return emprestimoRepository.findById(id).get();
		return amizadeRepository.findById(id).orElse(null);
	}

	@Transactional
    public Amizade findByUsuarioSeguidorAndUsuarioSeguido(Usuario id_usuarioSeguidor, Usuario id_usuarioSeguido) {
        Amizade amizade = new Amizade();
        amizade = amizadeRepository.findByUsuarioSeguidorAndUsuarioSeguido(id_usuarioSeguidor, id_usuarioSeguido);
        return amizade;
    }

	@Transactional
    public List<Amizade> findAllByUsuarioSeguido(Usuario id_usuarioSeguido) {
        List<Amizade> amizades = new ArrayList<>();
        amizades = amizadeRepository.findByUsuarioSeguido(id_usuarioSeguido);
        return amizades;
    }

    public Amizade saveAmizade(Amizade amizade) {
		return amizadeRepository.save(amizade);
	}

    public Amizade updateAmizade(Amizade amizade, Integer id) {
		
		Amizade amizadeExistenteNoBanco = getAmizadeById(id);
		
		amizadeExistenteNoBanco.setUsuarioSeguidor(amizade.getUsuarioSeguidor());
		amizadeExistenteNoBanco.setUsuarioSeguido(amizade.getUsuarioSeguido());
		
		
		return amizadeRepository.save(amizadeExistenteNoBanco);
	}

    public Amizade deleteAmizade(Integer id) {
		amizadeRepository.deleteById(id);
		return getAmizadeById(id);
	}
}
