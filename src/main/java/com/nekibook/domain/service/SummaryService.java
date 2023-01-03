package com.nekibook.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.repository.LeituraRepository;
import com.nekibook.domain.repository.SummaryRepository;
import com.nekibook.domain.repository.UsuarioRepository;

@Service
public class SummaryService {

    @Autowired
    SummaryRepository summaryRepository;
    
    @Autowired
    UsuarioRepository userRepository;

    @Autowired
	LeituraRepository leituraRepository;


    public List<Summary> getAllSummaries(){
		return summaryRepository.findAll();
	}

    public Summary getSummaryById(Integer id) {
		//return emprestimoRepository.findById(id).get();
		return summaryRepository.findById(id).orElse(null);
	}

    @Transactional
    public Summary findByLeiturasAndUsuarios(Leitura id_leitura, Usuario user_id) {
        Summary summary = new Summary();
        summary = summaryRepository.findByLeiturasAndUsuarios(id_leitura, user_id);
        return summary;
    }

	@Transactional
    public List<Summary> findAllByLeitura(Leitura id_leitura) {
        List<Summary> summaries = new ArrayList<>();
        summaries = summaryRepository.findByLeituras(id_leitura);
        return summaries;
    }

    public Summary saveSummary(Summary summary) {
		return summaryRepository.save(summary);
	}

    public Summary updateSummary(Summary summary, Integer id) {
		
		Summary summaryExistenteNoBanco = getSummaryById(id);
		
        summaryExistenteNoBanco.setSummary(summary.getSummary());
        // summaryExistenteNoBanco.setLeituras(summary.getLeituras());
        // summaryExistenteNoBanco.setUsuarios(summary.getUsuarios());

		return summaryRepository.save(summaryExistenteNoBanco);
	}

    public Summary deleteSummary(Integer id) {
		summaryRepository.deleteById(id);
		return getSummaryById(id);
	}
}
