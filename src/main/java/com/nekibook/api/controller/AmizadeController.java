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

import com.nekibook.domain.model.Amizade;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.service.AmizadeService;

@RestController
@RequestMapping("/amizade")
public class AmizadeController {
    
    @Autowired
	AmizadeService amizadeService;

    @GetMapping
	public ResponseEntity<List<Amizade>> getAllAmizade(){
		return new ResponseEntity<>(amizadeService.getAllAmizades(), HttpStatus.OK);	
	}

	@GetMapping("/userSeguidor/{id_usuarioSeguidor}/userSeguido/{id_usuarioSeguido}")
	public ResponseEntity<Amizade> findByUsuarioSeguidorAndUsuarioSeguido(
		@PathVariable Usuario id_usuarioSeguidor, @PathVariable Usuario id_usuarioSeguido
		) {
	  return new ResponseEntity<>(
		amizadeService.findByUsuarioSeguidorAndUsuarioSeguido(id_usuarioSeguidor, id_usuarioSeguido),
		HttpStatus.OK
	  );
	}

    @GetMapping("/{id}")
	public ResponseEntity<Amizade> getAmizadeById(@PathVariable Integer id) {
		Amizade amizade = amizadeService.getAmizadeById(id);
		if(null != amizade)
			return new ResponseEntity<>(amizade, HttpStatus.OK);
		else
			return new ResponseEntity<>(amizade, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/usuario/{id_usuarioSeguido}")
	public ResponseEntity<List<Amizade>> getByAmizade(@PathVariable Usuario id_usuarioSeguido) {
	  return new ResponseEntity<>(
		amizadeService.findAllByUsuarioSeguido(id_usuarioSeguido),
		HttpStatus.OK
	  );
	}

    @PostMapping
	public ResponseEntity<Amizade> saveAmizade(@RequestBody Amizade amizade) {
		return new ResponseEntity<>(amizadeService.saveAmizade(amizade),HttpStatus.CREATED);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Amizade> updateAmizade(@RequestBody Amizade amizade, @PathVariable Integer id){
		return new ResponseEntity<>(amizadeService.updateAmizade(amizade, id), HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Amizade> deleteAmizade(@PathVariable Integer id){
		Amizade amizade = amizadeService.getAmizadeById(id);
		if(null == amizade)
			return new ResponseEntity<>(amizade, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(amizadeService.deleteAmizade(id), HttpStatus.OK);
	}
}
