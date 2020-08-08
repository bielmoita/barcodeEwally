package com.saibaseubarcode.identificador.repository;

import java.util.List;

import com.saibaseubarcode.identificador.model.Linha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface LinhaRepository extends JpaRepository<Linha, String> {
	
	public List<Linha> findAllByLinhaDigitadaContainingIgnoreCase(String linhaDigitada);
	
	public List<Linha> findAllbyCalculaLinhaContainingIgnoreCase(String calculaLinha);

}
