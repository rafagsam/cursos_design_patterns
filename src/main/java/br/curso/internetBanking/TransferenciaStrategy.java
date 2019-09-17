package br.curso.internetBanking;

import java.math.BigDecimal;

public interface TransferenciaStrategy {

	public void transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor);
}
