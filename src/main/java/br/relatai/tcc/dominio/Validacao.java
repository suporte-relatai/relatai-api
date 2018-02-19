package br.relatai.tcc.dominio;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonInclude;

public class Validacao {

	@Id
	private String id;
	
	@JsonInclude(Include.NON_NULL)
	private String descricao;
}
