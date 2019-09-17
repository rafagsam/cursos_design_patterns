package br.curso.internetBanking;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class BancoTest {

	@Test
	public void deveAdicionarCliente() {
		var banco = new Banco();
		banco.adicionarCliente(c -> {
			c.nome = "Paulo";
			c.endereco= "Av. Augusto de Lima";
			c.sobrenome = "Lara";
			c.telefone = "(31) 333333333";
			c.contas = Arrays.asList(new ContaCorrente(), new ContaInvestimento(TipoAplicacao.LCI));			
		});
	}
	
	@Test
	public void deveAplicarTaxas() {
		var banco = new Banco();
		banco.adicionarCliente(c -> {
			c.nome = "Paulo";
			c.endereco= "Av. Augusto de Lima";
			c.sobrenome = "Lara";
			c.telefone = "(31) 333333333";
			c.contas = Arrays.asList(new ContaCorrente(), new ContaInvestimento(TipoAplicacao.CDB));			
		});
		banco.adicionarCliente(c -> {
			c.nome = "Rafael";
			c.endereco= "Av. Protasio de O Penna";
			c.sobrenome = "Sampaio";
			c.telefone = "(31) 333333333";
			c.contas = Arrays.asList(new ContaCorrente());			
		});
		Cliente clientePaulo = banco.recuperarClientePorNome("Paulo");		
		Cliente clienteRafael = banco.recuperarClientePorNome("Rafael");
		clienteRafael.getContas().get(0).deposito(new BigDecimal(10000));

		banco.aplicarTaxas();
		clientePaulo.getContas().forEach(c-> c.imprimeExtratoCompleto());
		clienteRafael.getContas().forEach(c-> c.imprimeExtratoCompleto());
	}
	
	@Test
	public void deveAplicarRendimentos() {
		var banco = new Banco();
		banco.adicionarCliente(c -> {
			c.nome = "Paulo";
			c.endereco= "Av. Augusto de Lima";
			c.sobrenome = "Lara";
			c.telefone = "(31) 333333333";
			c.contas = Arrays.asList(new ContaCorrente(), new ContaInvestimento(TipoAplicacao.CDB));			
		});
		banco.adicionarCliente(c -> {
			c.nome = "Rafael";
			c.endereco= "Av. Protasio de O Penna";
			c.sobrenome = "Sampaio";
			c.telefone = "(31) 333333333";
			c.contas = Arrays.asList(new ContaCorrente());			
		});
		Cliente clientePaulo = banco.recuperarClientePorNome("Paulo");
		clientePaulo.getContas().forEach(conta -> conta.deposito(new BigDecimal(100)));
		
		Cliente clienteRafael = banco.recuperarClientePorNome("Rafael");
		clienteRafael.getContas().get(0).deposito(new BigDecimal(10000));

		banco.aplicarRentabilidades();
		clientePaulo.getContas().forEach(c-> c.imprimeExtratoCompleto());
		clienteRafael.getContas().forEach(c-> c.imprimeExtratoCompleto());
	}
	
	
}
