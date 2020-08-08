package com.saibaseubarcode.identificador.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saibaseubarcode.identificador.model.Linha;
import com.saibaseubarcode.identificador.repository.LinhaRepository;

@RestController
@RequestMapping("/codigobarras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LinhaController {
	
	@Autowired
	private LinhaRepository repository;
	
	@GetMapping("/{linhaDigitada}")
	public ResponseEntity<List<Linha>> GetByLinhaDigitada(@PathVariable String linhaDigitada){
		return ResponseEntity.ok(repository.findAllByLinhaDigitadaContainingIgnoreCase(linhaDigitada));
	}
	
	
	@GetMapping("/{calculaLinha}")
	public ResponseEntity<List<Linha>> GetByLinhaCalculada(@PathVariable String calculaLinha){
		return ResponseEntity.ok(repository.findAllbyCalculaLinhaContainingIgnoreCase(calculaLinha));
	}

}
