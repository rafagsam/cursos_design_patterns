package br.curso.internetBanking;

import java.math.BigDecimal;

public enum TipoAplicacao {	
	CDB(conta-> conta.acrescentaRentabilidade(conta.getSaldo().multiply(new BigDecimal(0.002)).divide(new BigDecimal(100)))),
	TESOURO((conta-> conta.acrescentaRentabilidade(conta.getSaldo().multiply(new BigDecimal(0.001)).divide(new BigDecimal(100))))),
	LCI((conta-> conta.acrescentaRentabilidade(conta.getSaldo().multiply(new BigDecimal(0.003)).divide(new BigDecimal(100))))),
	DEBENTURES((conta-> conta.acrescentaRentabilidade(conta.getSaldo().multiply(new BigDecimal(0.0033)).divide(new BigDecimal(100)))));

	private CalculoRentabilidadeStrategy calculoRentabilidadeStrategy;
	
	TipoAplicacao(CalculoRentabilidadeStrategy calculoRentabilidadeStrategy) {
		this.calculoRentabilidadeStrategy = calculoRentabilidadeStrategy;
	}

	public CalculoRentabilidadeStrategy getCalculoRentabilidadeStrategy() {
		return calculoRentabilidadeStrategy;
	}
	
	
}
