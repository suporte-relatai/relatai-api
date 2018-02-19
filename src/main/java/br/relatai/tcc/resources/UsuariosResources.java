package br.relatai.tcc.resources;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.relatai.tcc.dominio.Usuario;
import br.relatai.tcc.services.UsuariosService;

@RestController
@RequestMapping("/usuarios")
public class UsuariosResources {

	@Autowired
	private UsuariosService usuariosService;
			
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Usuario usuario) {
		
		usuario = usuariosService.salvar(usuario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
	
	@GetMapping(path = "/{celular}")
	public ResponseEntity<?> buscarPeloCelular(@PathVariable("celular") String celular) {				
		Usuario usuario = usuariosService.buscarPeloCelular(celular);
		return ResponseEntity.status(HttpStatus.OK).body(usuario);
	}
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(usuariosService.listar());
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") String id){
		usuariosService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
