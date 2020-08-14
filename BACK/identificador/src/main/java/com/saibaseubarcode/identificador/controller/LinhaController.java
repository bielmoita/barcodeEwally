package com.saibaseubarcode.identificador.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.saibaseubarcode.identificador.model.Linha;
import com.saibaseubarcode.identificador.repository.LinhaRepository;

@RestController
@RequestMapping("/codigobarras")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LinhaController {
	
	@Autowired
	private LinhaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Linha>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{codeBarDigitado}")
	public ResponseEntity<List<Linha>> GetBycodeBarDigitado(@PathVariable String codeBarDigitado){
		return ResponseEntity.ok(repository.findAllByCodeBarDigitadoContainingIgnoreCase(codeBarDigitado));
	}
	
	//@GetMapping("/calculalinha")
	//public ResponseEntity<List<Linha>> GetByCalculaLinha(@PathVariable String codeBarDigitado){
		//return ResponseEntity.ok(repository.calculaLinha(codeBarDigitado);
	//}
	
	@RequestMapping(method=RequestMethod.GET, value="/calculalinha/{codeBarDigitado}")
	@ResponseBody
	public List<Linha> calculaLinha(@PathVariable(value = "codeBarDigitado") String codeBarDigitado){
		return repository.calculaLinha(codeBarDigitado);
	}
	
	
	
	
	//@PostMapping(path = "/{codeBarDigitado}")
	
	@RequestMapping(method=RequestMethod.POST , path="/calculalinha/{codeBarDigitado}")
	public ResponseEntity<Linha> post (@RequestBody Linha codeBarDigitado){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(codeBarDigitado));
	}
	
	//@PostMapping
	//public ResponseEntity<Linha> post (@RequestBody Linha calculaLinha){
		//return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(calculaLinha));
	//}
	
	

}
