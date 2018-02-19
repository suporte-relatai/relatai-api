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
import br.relatai.tcc.dominio.Categoria;
import br.relatai.tcc.dominio.Relato;
import br.relatai.tcc.services.CategoriasService;

@RestController
@RequestMapping("/categorias")
public class CategoriasResources {

	@Autowired
	private CategoriasService categoriasService;	
			
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Categoria categoria) {
		
		categoria = categoriasService.salvar(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}	
	
	@GetMapping(path = "/{nome}")
	public ResponseEntity<?> buscarPeloNome(@PathVariable String nome) {				
		Categoria categoria = categoriasService.buscarPeloNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(categoria);
	}
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(categoriasService.listar());
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletar(@PathVariable String id){
		categoriasService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping(path = "/{cid}/relatos")
	public ResponseEntity<Void> relatar(@PathVariable String cid, @Valid @RequestBody Relato relato) {
				
		Categoria categoria = categoriasService.relatar(cid, relato);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
