package br.curso.internetBanking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferenciaContaComTaxaStrategy implements TransferenciaStrategy {

	@Override
	public void transferencia(Conta contaOrigem, Conta contaDestino, BigDecimal valor) {
		
		contaOrigem.debito(valor);
		
		Movimentacao movimentacao = new Movimentacao.Builder().set(m -> {
			m.conta = contaOrigem;
			m.tipoMovimentacao = TipoMovimentacao.TRANSFERENCIA;
			m.dataMovimentacao = LocalDateTime.now();
			m.valor = valor.negate();
		}).build();		
		
		contaOrigem.movimentaConta(movimentacao);
		
		contaDestino.credito(valor);
		
		
		
		
	}

}
