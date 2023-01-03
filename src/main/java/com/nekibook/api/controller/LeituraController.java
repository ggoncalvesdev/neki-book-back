package com.nekibook.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekibook.domain.model.Leitura;
import com.nekibook.domain.model.Livro;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.model.dto.LeituraDTO;
import com.nekibook.domain.service.LeituraService;

@RestController
@RequestMapping("/leitura")
public class LeituraController {

    @Autowired
	LeituraService leituraService;

    @GetMapping
	public ResponseEntity<List<Leitura>> getAllLeituras(){
		return new ResponseEntity<>(leituraService.getAllLeituras(), HttpStatus.OK);	
	}

    @GetMapping("/dto")
	public ResponseEntity<List<LeituraDTO>> getAllLeiturasDTO(){
		return new ResponseEntity<>(leituraService.getAllLeiturasDTO(), HttpStatus.OK);	
	}
    
    @GetMapping("/{id}")
	public ResponseEntity<Leitura> getLeituraById(@PathVariable Integer id) {
		Leitura leitura = leituraService.getLeituraById(id);
		if(null != leitura)
			return new ResponseEntity<>(leitura, HttpStatus.OK);
		else
			return new ResponseEntity<>(leitura, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/livro/{idlivro}/usuario/{user_id}")
	public ResponseEntity<Leitura> getByLivroUsuario(@PathVariable Livro idlivro, @PathVariable Usuario user_id) {
	  return new ResponseEntity<>(
		leituraService.findByLivroAndUsuario(idlivro, user_id),
		HttpStatus.OK
	  );
	}

    @PostMapping
	public ResponseEntity<Leitura> saveLeitura(@RequestBody Leitura leitura) {
		return new ResponseEntity<>(leituraService.saveLeitura(leitura),HttpStatus.CREATED);
	}

    @PostMapping("/dto")
	public ResponseEntity<LeituraDTO> saveLeituraDTO(@RequestBody LeituraDTO leituraDTO) {
		return new ResponseEntity<>(leituraService.saveLeituraDTO(leituraDTO),HttpStatus.CREATED);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Leitura> updateLeitura(@RequestBody Leitura leitura, @PathVariable Integer id){
		return new ResponseEntity<>(leituraService.updateLeitura(leitura, id), HttpStatus.OK);
	}

    @PutMapping("/dto/{id}")
	public ResponseEntity<LeituraDTO> updateLeituraDTO(@RequestBody LeituraDTO leituraDTO, @PathVariable Integer id){
		return new ResponseEntity<>(leituraService.updateLeituraDTO(leituraDTO, id), HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Leitura> deleteLeitura(@PathVariable Integer id){
		Leitura leitura = leituraService.getLeituraById(id);
		if(null == leitura)
			return new ResponseEntity<>(leitura, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(leituraService.deleteLeitura(id), HttpStatus.OK);
	}
}
