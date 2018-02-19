package br.relatai.tcc.services;

import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.relatai.tcc.dominio.Usuario;
import br.relatai.tcc.repository.UsuariosRepository;
import br.relatai.tcc.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	public List<Usuario> listar(){
		return usuariosRepository.findAll();
	}
	
	public Usuario buscarPorId(String id) {
		Usuario usuario = usuariosRepository.findOne(id);
		
		if(usuario == null) {
			throw new UsuarioNaoEncontradoException("O usuário não pôde ser encontrado.");
		}
		
		return usuario;
	}

	public Usuario buscarPeloCelular(String celular) {	
		Usuario usuario = usuariosRepository.findByCelular(cifrarCelular(celular));		
		
		if(usuario == null) {
			throw new UsuarioNaoEncontradoException("O usuário não pôde ser encontrado.");
		}
		
		return usuario;
	}
	
	public Usuario salvar(Usuario usuario) {		
		usuario.setId(null);			
		usuario.setCelular(cifrarCelular(usuario.getCelular()));
		return usuariosRepository.save(usuario);
	}
	
	public void deletar(String id) {
		try {
			usuariosRepository.delete(id);
		}catch (UsuarioNaoEncontradoException e) {
			throw new UsuarioNaoEncontradoException("O usuário não pôde ser encontrado.");
		}		
	}
	
	private String cifrarCelular(String celular) {
		return Base64.encodeBase64String(celular.getBytes());
	}		
}
