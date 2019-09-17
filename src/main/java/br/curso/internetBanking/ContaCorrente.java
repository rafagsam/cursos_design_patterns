package br.curso.internetBanking;

import java.math.BigDecimal;

public class ContaCorrente extends Conta {
	private BigDecimal limiteCredito;

	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	@Override
	public void accept(TaxaContaVisitor visitor) {
		visitor.visitContaCorrente(this);
	}
	
}
