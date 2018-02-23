package br.relatai.tcc.services;

import java.time.LocalDate;
import java.util.ArrayList;
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
		List<Usuario> usuarios = usuariosRepository.findAll();
		List<Usuario> usuariosDescifrados = new ArrayList<>();
		for(Usuario u : usuarios) {
			u.setCelular(descifrarCelular(u.getCelular()));
			usuariosDescifrados.add(u);
		}
		return usuarios;
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
	
	private String descifrarCelular(String cifra) {
		byte[] decodedBytes = Base64.getDecoder().decode(cifra);
		String decodedString = new String(decodedBytes);
		return decodedString;
	}
}