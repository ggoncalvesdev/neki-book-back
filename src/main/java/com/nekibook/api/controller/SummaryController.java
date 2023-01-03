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
import com.nekibook.domain.model.Summary;
import com.nekibook.domain.model.Usuario;
import com.nekibook.domain.service.SummaryService;

@RestController
@RequestMapping("/summary")
public class SummaryController {
    
    @Autowired
	SummaryService summaryService;

    @GetMapping
	public ResponseEntity<List<Summary>> getAllSummaries(){
		return new ResponseEntity<>(summaryService.getAllSummaries(), HttpStatus.OK);	
	}

    @GetMapping("/{id}")
	public ResponseEntity<Summary> getLeituraById(@PathVariable Integer id) {
		Summary summary = summaryService.getSummaryById(id);
		if(null != summary)
			return new ResponseEntity<>(summary, HttpStatus.OK);
		else
			return new ResponseEntity<>(summary, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/leitura/{id_leitura}/usuario/{user_id}")
	public ResponseEntity<Summary> getByLeiturasAndUsuarios(
		@PathVariable Leitura id_leitura, @PathVariable Usuario user_id
		) {
	  return new ResponseEntity<>(
		summaryService.findByLeiturasAndUsuarios(id_leitura, user_id),
		HttpStatus.OK
	  );
	}

	@GetMapping("/leitura/{id_leitura}")
	public ResponseEntity<List<Summary>> getByLeitura(@PathVariable Leitura id_leitura) {
	  return new ResponseEntity<>(
		summaryService.findAllByLeitura(id_leitura),
		HttpStatus.OK
	  );
	}

    @PostMapping
	public ResponseEntity<Summary> saveSummary(@RequestBody Summary summary) {
		return new ResponseEntity<>(summaryService.saveSummary(summary),HttpStatus.CREATED);
	}

    @PutMapping("/{id}")
	public ResponseEntity<Summary> updateLeitura(@RequestBody Summary summary, @PathVariable Integer id){
		return new ResponseEntity<>(summaryService.updateSummary(summary, id), HttpStatus.OK);
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<Summary> deleteSummary(@PathVariable Integer id){
		Summary summary = summaryService.getSummaryById(id);
		if(null == summary)
			return new ResponseEntity<>(summary, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(summaryService.deleteSummary(id), HttpStatus.OK);
	}
}
