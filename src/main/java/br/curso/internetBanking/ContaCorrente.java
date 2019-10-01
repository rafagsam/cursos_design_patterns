package br.curso.internetBanking;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ContaCorrente extends Conta {
	@Column
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
