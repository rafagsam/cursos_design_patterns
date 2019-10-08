package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContaService {

	@Autowired
	ContaRepository contaRepository;
	@Autowired
	ClienteRepository clienteRepository;


	public void adicionarConta(Conta conta) {		
		Cliente cliente = conta.getCliente();
		var clienteDB = clienteRepository.findById(cliente.getId());
		if (clienteDB.isPresent()) {
			conta.setCliente(clienteDB.get());
			contaRepository.save(conta);
		} else {
			throw new RuntimeException("Cliente não encontrado");
		}

	}
	public void removerConta(Conta conta) {
		contaRepository.delete(conta);
	}

	public void atualizarConta(Conta conta) {
		contaRepository.save(conta);
	}

	public List<Conta>pesquisarContasCliente(Cliente cliente) {
		return contaRepository.findAllByCliente(cliente);
	}
	public Optional<Conta> recuperarporIdConta(Long id) {
		return contaRepository.findById(id);
	}
	
	public void deposito(Long idConta, BigDecimal valor) {
		var contaDB = contaRepository.findById(idConta);
		Conta c = contaDB.get();
		c.adicionarObservador(COAF.getInstance());
		c.deposito(valor);
		contaRepository.save(contaDB.get());		
		
	}
	
	public void saque(Long idConta, BigDecimal valor) {
		var contaDB = contaRepository.findById(idConta);
		Conta c = contaDB.get();
		c.adicionarObservador(COAF.getInstance());
		c.saque(valor);
		contaRepository.save(contaDB.get());		
	}
	
	public List<Movimentacao> recuperarExtratoPeloIdConta(Long idConta) {
		var contaDB = contaRepository.findById(idConta);
		return contaDB.get().getMovimentacoes();
	}
}
