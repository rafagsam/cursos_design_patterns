package br.curso.internetBanking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class ContaTest {

	@Test
	public void deveDepositarConta() {
		Conta conta = new ContaCorrente();
		conta.deposito(new BigDecimal(1000));
		assertEquals(new BigDecimal(1000), conta.getSaldo());
	}
	@Test
	public void deveImprimirMovimentacoes() {
		Conta conta = new ContaCorrente();
		conta.deposito(new BigDecimal(1000));
		conta.imprimeExtratoCompleto();
	}
	
}
