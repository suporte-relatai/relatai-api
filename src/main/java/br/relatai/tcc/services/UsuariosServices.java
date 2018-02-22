package br.relatai.tcc.services;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.relatai.tcc.dominio.Usuario;
import br.relatai.tcc.repository.UsuariosRepository;

@Service
public class UsuariosServices {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	public List<Usuario> listar(){
		return usuariosRepository.findAll();
	}

	public Usuario buscarPeloCelular(String celular) {	
		Usuario usuario = usuariosRepository.findByCelular(cifrarCelular(celular));		
		if(usuario == null) {
			usuario = new Usuario(null, cifrarCelular(celular), LocalDate.now());
			usuariosRepository.save(usuario);
		}		
		return usuario;
	}
	
	private String cifrarCelular(String celular) {
		return Base64.getEncoder().encodeToString(celular.getBytes());
	}			
}