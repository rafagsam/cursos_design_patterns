package br.curso.internetBanking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ClienteRepository extends CrudRepository<Cliente, Long>{
	public abstract List<Cliente> findAllByNome(String nome);
}
