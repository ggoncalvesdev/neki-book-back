package com.nekibook.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.dto.ConsultaLivroDTO;
import com.nekibook.domain.model.dto.ItemDTO;
import com.nekibook.domain.service.LivroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

  @Autowired
  LivroService livroService;

  @GetMapping
  public ResponseEntity<List<Livro>> getAllLivros() {
    return new ResponseEntity<>(livroService.getAllLivros(), HttpStatus.OK);
  }

  @GetMapping("/nome/{nomelivro}")
  public ResponseEntity<List<Livro>> getByNome(@PathVariable String nomelivro) {
    return new ResponseEntity<>(
      livroService.findByNomeLivro(nomelivro),
      HttpStatus.OK
    );
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> getLivroById(@PathVariable Integer id) {
    Livro livro = new Livro();

    try {
      livro = livroService.getLivroById(id);
      return new ResponseEntity<>(livro, HttpStatus.OK);
    } catch (Exception ex) {
      //throw new NoSuchElementFoundException("Não foi encontrado resultado com o id " + id);
      return new ResponseEntity<>(livro, HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/consulta-livro/{isbn}")
  public ResponseEntity<ItemDTO> consultaLivroApiExterna(
    @PathVariable String isbn
  ) throws JsonMappingException, JsonProcessingException {
    ItemDTO consultaLivroDTO = livroService.consultaLivroApiExterna(isbn);
    if (null != consultaLivroDTO) return new ResponseEntity<>(
      consultaLivroDTO,
      HttpStatus.OK
    ); else return new ResponseEntity<>(consultaLivroDTO, HttpStatus.NOT_FOUND);
  }

  @GetMapping("/isbn/{isbn}")
  public ResponseEntity<Livro> saveLivroFromApiExterna(
    @PathVariable String isbn
  ) {
    return new ResponseEntity<>(
      livroService.saveLivroFromApiExterna(isbn),
      HttpStatus.CREATED
    );
  }

  @PostMapping
  public ResponseEntity<Livro> saveLivro(@RequestBody Livro livro) {
    return new ResponseEntity<>(
      livroService.saveLivro(livro),
      HttpStatus.CREATED
    );
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Livro> deleteLivro(@PathVariable Integer id) {
    Livro livro = livroService.getLivroById(id);
    if (null == livro) return new ResponseEntity<>(
      livro,
      HttpStatus.NOT_FOUND
    ); else return new ResponseEntity<>(
      livroService.deleteLivro(id),
      HttpStatus.OK
    );
  }
}
