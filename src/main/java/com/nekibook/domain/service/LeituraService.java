package com.nekibook.domain.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.model.dto.LeituraDTO;
import com.nekibook.domain.model.dto.LivroDTO;
import com.nekibook.domain.repository.LeituraRepository;
import com.nekibook.domain.repository.LivroRepository;
import com.nekibook.domain.repository.UsuarioRepository;

@Service
public class LeituraService {

    @Autowired
	LeituraRepository leituraRepository;

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    UsuarioRepository userRepository;
    
    public List<Leitura> getAllLeituras(){
		return leituraRepository.findAll();
	}

    public List<LeituraDTO> getAllLeiturasDTO(){
		List<LeituraDTO> leiturasDTO = new ArrayList<>();
		List<Leitura> leituras = leituraRepository.findAll();
		for(Leitura leitura : leituras) {
			leiturasDTO.add(this.toDTO(leitura));
		}
		return leiturasDTO;
	}

    public Leitura getLeituraById(Integer id) {
		//return emprestimoRepository.findById(id).get();
		return leituraRepository.findById(id).orElse(null);
	}

	@Transactional
	public Leitura findByLivroAndUsuario(Livro idlivro, Usuario user_id) {
        Leitura leitura = new Leitura();
        leitura = leituraRepository.findByLivrosAndUsuarios(idlivro, user_id);
        return leitura;
    }

    public Leitura saveLeitura(Leitura leitura) {
		return leituraRepository.save(leitura);
	}

    public LeituraDTO saveLeituraDTO(LeituraDTO leituraDTO) {
		Leitura leitura = toEntidade(leituraDTO);
		Leitura novoLeitura = leituraRepository.save(leitura);
		
		LeituraDTO leituraAtualizadoDTO = toDTO(novoLeitura);		
		return leituraAtualizadoDTO;
	}

    public LeituraDTO updateLeituraDTO(LeituraDTO leituraDTO, Integer id) {
		Leitura leituraExistenteNoBanco = getLeituraById(id);
		LeituraDTO leituraAtualizadoDTO = new LeituraDTO();

		if(leituraExistenteNoBanco != null) {
			
			leituraExistenteNoBanco = toEntidade(leituraDTO);
			
			Leitura leituraAtualizado = leituraRepository.save(leituraExistenteNoBanco);
			
			leituraAtualizadoDTO = toDTO(leituraAtualizado);
		}
		return leituraAtualizadoDTO;
	}

    public Leitura toEntidade (LeituraDTO leituraDTO) {
		Leitura leitura = new Leitura();
		Livro livro = livroRepository.findById(leituraDTO.getLivros().getIdLivro()).orElse(null);
        // Usuario usuario = userRepository.findById(leituraDTO.getUsuarios().getId()).orElse(null);
		leitura.setLivros(livro);
		// leitura.setUsuarios(usuario);
		
		return leitura;
	}

    public LeituraDTO toDTO (Leitura leitura) {
		LeituraDTO leituraDTO = new LeituraDTO();
		Livro livro = livroRepository.findById(leitura.getLivros().getIdLivro()).orElse(null);
        Usuario usuario = userRepository.findById(leitura.getUsuarios().getId()).orElse(null);
		LivroDTO livroDTO = new LivroDTO();
		livroDTO.setLeituras(getAllLeiturasDTO());
		// livroDTO.setAutor(getAutor());

		// leituraDTO.setLivros(livro);
		// leituraDTO.setUsuarios(usuario);		
		
		return leituraDTO;
	}

    public Leitura updateLeitura(Leitura leitura, Integer id) {
		
		Leitura leituraExistenteNoBanco = getLeituraById(id);
		
		leituraExistenteNoBanco.setLivros(leitura.getLivros());
		leituraExistenteNoBanco.setUsuarios(leitura.getUsuarios());
		leituraExistenteNoBanco.setStatus(leitura.getStatus());
		
		return leituraRepository.save(leituraExistenteNoBanco);
	}

    public Leitura deleteLeitura(Integer id) {
		leituraRepository.deleteById(id);
		return getLeituraById(id);
	}
}
