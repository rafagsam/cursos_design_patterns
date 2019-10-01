package br.curso.internetBanking;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public void adicionarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	public void removerCliente(Cliente cliente) {
		clienteRepository.delete(cliente);
	}
	public void atualizarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public List<Cliente> pesquisarCliente(String nome) {
		return clienteRepository.findAllByNome(nome);
	}
	public Optional<Cliente> recuperarporIdCliente(Long id) {
		return clienteRepository.findById(id);
	}
}
