package br.relatai.tcc.dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "relato")
public class Relato {
	
	private String id;		
	@DBRef
	private Usuario usuario;
	private LocalDate dataPublicacao;		
	private LocalTime horaPublicacao;		
	private String descricao;		
	private Double latitude;		
	private Double longitude;		
	private String foto;	
	private Integer confirmado;	
	private Integer denunciado;	
	@DBRef
	private List<Validacao> validacoes;

	@Id
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}		
	
	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usuario) {this.usuario = usuario;}
	
	@NotNull(message = "A data de publicação é obrigatória.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Field("data_publicacao")
	public LocalDate getDataPublicacao() {return dataPublicacao;}
	public void setDataPublicacao(LocalDate dataPublicacao) {this.dataPublicacao = dataPublicacao;}
	
	@NotNull(message = "A hora de publicação é obrigatória.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", locale = "pt-BR", timezone = "America/Sao_Paulo")
	@Field("hora_publicacao")
	public LocalTime getHoraPublicacao() {return horaPublicacao;}
	public void setHoraPublicacao(LocalTime horaPublicacao) {this.horaPublicacao = horaPublicacao;}
	
	@JsonInclude(Include.NON_NULL)
	public Integer getConfirmado() {return confirmado;}
	public void setConfirmado(Integer confirmado) {this.confirmado = confirmado;}

	@JsonInclude(Include.NON_NULL)
	public Integer getDenunciado() {return denunciado;}
	public void setDenunciado(Integer denunciado) {this.denunciado = denunciado;}
	
	@NotBlank(message = "O relato do problema é obrigatório.")
	@JsonInclude(Include.NON_NULL)
	public String getDescricao() {return descricao;}
	public void setDescricao(String descricao) {this.descricao = descricao;}

	@NotNull(message = "A latitude é obrigatória.")
	public Double getLatitude() {return latitude;}
	public void setLatitude(Double latitude) {this.latitude = latitude;}
	
	@NotNull(message = "A longitude é obrigatória.")
	public Double getLongitude() {return longitude;}
	public void setLongitude(Double longitude) {this.longitude = longitude;}

	@NotBlank(message = "A foto é obrigatória.")
	public String getFoto() {return foto;}
	public void setFoto(String foto) {this.foto = foto;}
	
	@JsonInclude(Include.NON_NULL)
	public List<Validacao> getValidacoes() {return validacoes;}
	public void setValidacoes(List<Validacao> validacoes) {this.validacoes = validacoes;}
}
