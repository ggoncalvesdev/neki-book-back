package com.nekibook.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.dto.ConsultaLivroDTO;
import com.nekibook.domain.model.dto.ItemDTO;
import com.nekibook.domain.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    LivroRepository livroRepository;

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivroById(Integer id) {
        // return enderecoRepository.findById(id).orElse(null);
        return livroRepository.findById(id).get();
    }

    @Transactional
    public List<Livro> findByNomeLivro(String nomelivro) {
        List<Livro> livros = new ArrayList<>();
        livros = livroRepository.findAllByNomeLivroContainingIgnoreCase(nomelivro);
        return livros;
    }

    public ItemDTO consultaLivroApiExterna(String isbn) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "https://www.googleapis.com/books/v1/volumes?q={isbn}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("isbn", isbn);

        ConsultaLivroDTO consultaLivroDTO = restTemplate.getForObject(uri, ConsultaLivroDTO.class, params);
        // System.out.println(consultaLivroDTO.getItems().toString());

        List<ItemDTO> teste = consultaLivroDTO.getItems();

        System.out.println(teste.get(0).getId());
        return teste.get(0);
    }

    public Livro saveLivroFromApiExterna(String isbn) {
        ItemDTO consultaLivroDTO = consultaLivroApiExterna(isbn);

        Livro livro = new Livro();
        livro.setNomeLivro(consultaLivroDTO.getVolumeInfo().getTitle());
        List<String> lista = consultaLivroDTO.getVolumeInfo().getAuthors();

        livro.setAutor(lista.get(0));

        if (consultaLivroDTO.getVolumeInfo().getImageLinks() == null) {
            return livroRepository.save(livro);
        } else {
            livro.setImagem(consultaLivroDTO.getVolumeInfo().getImageLinks().getThumbnail());
            return livroRepository.save(livro);
        }
    }

    public Livro saveLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro deleteLivro(Integer id) {
        livroRepository.deleteById(id);
        return getLivroById(id);
    }
}