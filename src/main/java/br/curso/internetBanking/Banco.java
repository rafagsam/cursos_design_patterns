package br.curso.internetBanking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Banco {
	private List<Cliente> clientes = new ArrayList<>();

	public void adicionarCliente(Consumer<Cliente.Builder> clienteConsumer) {
		Cliente cliente = new Cliente.Builder().set(clienteConsumer).build();
		clientes.add(cliente);
	}

	public void removerCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	
	public Cliente recuperarClientePorNome (String nome) {
		return this.clientes.stream()
				.filter((cliente) -> cliente.getNome().equals(nome))
				.findFirst()
				.orElse(null);
	}
	public void abrirConta(Cliente cliente, Conta conta) {

	}
	public void encerrarConta(Cliente cliente, Conta conta) {

	}

	public void aplicarTaxas() {
		this.clientes.stream()
		.flatMap(c -> c.getContas().stream())
		.forEach(conta->{
			conta.accept(new TaxaContaCorrenteVisitor());
			conta.accept(new TaxaContaInvestimentoVisitor());
		});
	}
	public void aplicarRentabilidades() {
		this.clientes.stream()
		.flatMap(c -> c.getContas().stream())
		.forEach(Conta::calculaRentabilidade);
	}

}
