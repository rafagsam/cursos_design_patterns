package br.curso.internetBanking;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface ContaRepository extends CrudRepository<Conta, Long>{
	public abstract List<Conta> findAllByCliente(Cliente cliente);
}
