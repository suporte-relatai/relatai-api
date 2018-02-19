package br.relatai.tcc.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.relatai.tcc.dominio.Categoria;
import br.relatai.tcc.dominio.Relato;
import br.relatai.tcc.repository.CategoriasRepository;
import br.relatai.tcc.repository.RelatosRepository;
import br.relatai.tcc.services.exceptions.CategoriaNaoEncontradaException;
import br.relatai.tcc.services.exceptions.UsuarioNaoEncontradoException;

@Service
public class CategoriasService {

	@Autowired
	private CategoriasRepository categoriasRepository;
	
	@Autowired
	private RelatosRepository relatosRepository;
	
	public List<Categoria> listar(){		
		return categoriasRepository.findBySituacaoTrue();
	}
	
	public Categoria buscarPorId(String id) {
		Categoria categoria = categoriasRepository.findOne(id);
		
		if(categoria == null) {
			throw new CategoriaNaoEncontradaException("A categoria não pôde ser encontrada.");
		}
		
		return categoria;
	}

	public Categoria buscarPeloNome(String nome) {	
		Categoria categoria = categoriasRepository.findByNomeIgnoreCaseContainingAndSituacaoTrue(nome);		
		
		if(categoria == null) {
			throw new CategoriaNaoEncontradaException("A categoria não pôde ser encontrada.");
		}
		
		return categoria;
	}
	
	public Categoria salvar(Categoria categoria) {		
		categoria.setId(null);		
		return categoriasRepository.save(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		verificarExistencia(categoria);
		categoriasRepository.save(categoria);
	}
	
	private void verificarExistencia(Categoria categoria) {
		buscarPorId(categoria.getId());
	}
	
	public Categoria relatar(String cid, Relato relato) {
		Categoria categoria = buscarPorId(cid);
		relatosRepository.save(relato);
		
		if(categoria.getRelatos() == null) {
			List<Relato> relatos = new ArrayList<>();		
			relatos.add(relato);
			categoria.setRelatos(relatos);
		}else {
			categoria.getRelatos().add(relato);
		}
		
		atualizar(categoria);
		
		return categoria;
	}
	
	public void deletar(String id) {
		try {
			categoriasRepository.delete(id);
		}catch (CategoriaNaoEncontradaException e) {
			throw new UsuarioNaoEncontradoException("A categoria não pôde ser encontrada.");
		}		
	}		
}
