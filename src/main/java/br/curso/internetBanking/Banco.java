package br.curso.internetBanking;

import java.util.ArrayList;
import java.util.List;

public class Banco {
	private List<Cliente> clientes = new ArrayList<>();
	
	public void adicionarCliente(Cliente cliente) {
		clientes.add(cliente);
	}
	
	public void removerCliente(Cliente cliente) {
		clientes.remove(cliente);
	}
	public void abrirConta(Cliente cliente, Conta conta) {
		
	}
	public void encerrarConta(Cliente cliente, Conta conta) {
		
	}
}
