package br.curso.internetBanking;

import java.math.BigDecimal;

public class TaxaContaCorrenteVisitor extends TaxaContaAbstractVisitor {
	@Override
	public void visitContaCorrente(ContaCorrente contaCorrente) {
		super.visitContaCorrente(contaCorrente);
		contaCorrente.taxar(BigDecimal.ONE);
	}
}
