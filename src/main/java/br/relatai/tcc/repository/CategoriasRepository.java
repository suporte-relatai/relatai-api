package br.relatai.tcc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.relatai.tcc.dominio.Categoria;

public interface CategoriasRepository extends MongoRepository<Categoria, String> {

	Categoria findByNomeIgnoreCaseContainingAndSituacaoTrue(String nome);
	List<Categoria> findBySituacaoTrue();
}
