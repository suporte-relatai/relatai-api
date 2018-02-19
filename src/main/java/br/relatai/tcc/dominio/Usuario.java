package br.relatai.tcc.dominio;

import java.time.LocalDate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection="usuario")
public class Usuario {

	private String id;
	
	@Indexed(unique = true)
	private String celular;
	
	private LocalDate dataCadastro;
	
	@Id
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
		
	@NotBlank(message = "O número do celular é obrigatório.")
	@Field("celular")	
	public String getCelular() {return celular;}
	public void setCelular(String celular) {this.celular = celular;}
			
	@Field("data_cadastro") @JsonFormat(pattern = "dd/MM/yyyy")	
	public LocalDate getDataCadastro() {return dataCadastro;}
	public void setDataCadastro(LocalDate dataCadastro) {this.dataCadastro = dataCadastro;}	
}
