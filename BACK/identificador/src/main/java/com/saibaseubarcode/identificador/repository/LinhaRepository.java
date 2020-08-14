package com.saibaseubarcode.identificador.repository;

import java.util.List;

import com.saibaseubarcode.identificador.model.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, String> {
	
	public List<Linha> findAllByCodeBarDigitadoContainingIgnoreCase(String codeBarDigitado);
	
	//public List<Linha> findAllbyCalculaLinhaContainingIgnoreCase(String calculaLinha);
	
	public List<Linha> calculaLinha(String codeBarDigitado);

}
