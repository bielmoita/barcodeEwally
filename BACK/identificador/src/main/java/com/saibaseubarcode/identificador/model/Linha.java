package com.saibaseubarcode.identificador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "TB_CODIGOBARRAS")
public class Linha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigoConsulta;
	
	@NotNull
	@Size(min =44, max = 46)
	public String codeBarDigitado;
	
	@NotNull
	public String calculaLinha;


	//Getters and Setters
	public long getCodigoConsulta() {
		return codigoConsulta;
	}

	public void setCodigoConsulta(long codigoConsulta) {
		this.codigoConsulta = codigoConsulta;
	}

	public String getCodeBarDigitado() {
		return codeBarDigitado;
	}

	public void setCodeBarDigitado(String codeBarDigitado) {
		this.codeBarDigitado = codeBarDigitado;
	}
	
	public String getCalculaLinha() {
		return calculaLinha;
	}

	public void setCalculaLinha(String calculaLinha) {
		this.calculaLinha = calculaLinha;
	}
	
	//public String calculaLinha() {
		//return codeBarDigitado;
	//}
	
	//Outros Métodos
	
	public String calculaLinha(String codeBarDigitado) {
		String feito;
		String linha = codeBarDigitado.replaceAll("[^0-9]", "");
		
		if (linha.length() != 44) {
			return "ERRO CODIGO DE BARRAS";
		}
		
		String campo1 = linha.substring(0,4)+linha.substring(19,20)+'.'+linha.substring(20,24);
	    String campo2 = linha.substring(24,29)+'.'+linha.substring(29,34);
	    String campo3 = linha.substring(34,39)+'.'+linha.substring(39,44);
	    String campo4 = linha.substring(4,5); // Digito verificador
	    String campo5 = linha.substring(5,19); // Vencimento + Valor
	    
	    if ( modulo11Banco( linha.substring(0,4)+linha.substring(5,44) ) != Integer.valueOf(campo4) ) {
	    	return null;
	    }
	    feito = campo1 + modulo10(campo1)
	    +' '
	    +campo2 + modulo10(campo2)
        +' '
        +campo3 + modulo10(campo3)
        +' '
        +campo4
        +' '
        +campo5
        ;
	    
	    setCalculaLinha(feito);
	    codeBarDigitado = feito;
	    return getCalculaLinha();
}

	public int modulo10(String numero) {
			numero = numero.replaceAll("[^0-9]","");
			int soma  = 0;
			int peso  = 2;
			int contador = numero.length()-1;
			while (contador >= 0) {
				int multiplicacao = Integer.valueOf( numero.substring(contador,contador+1) ) * peso;
				if (multiplicacao >= 10) {multiplicacao = 1 + (multiplicacao-10);}
				soma = soma + multiplicacao;
				if (peso == 2) {
					peso = 1;
				} else {
					peso = 2;
				}
				contador = contador - 1;
			}
			int digito = 10 - (soma % 10);
			if (digito == 10) digito = 0;

			return digito;
	}

	public int modulo11Banco(String numero) {
		numero = numero.replaceAll("[^0-9]","");

		int soma  = 0;
		int peso  = 2;
		int base  = 9;
		int contador = numero.length() - 1;
		for (int i=contador; i >= 0; i--) {
			soma = soma + ( Integer.valueOf(numero.substring(i,i+1)) * peso);
			if (peso < base) {
				peso++;
			} else {
				peso = 2;
			}
		}
		int digito = 11 - (soma % 11);
		if (digito >  9) digito = 0;
		// Utilizar o dígito 1(um) sempre que o resultado do cálculo padrão for igual a 0(zero), 1(um) ou 10(dez).
		if (digito == 0) digito = 1;
		return digito;
		
	}
	

}
