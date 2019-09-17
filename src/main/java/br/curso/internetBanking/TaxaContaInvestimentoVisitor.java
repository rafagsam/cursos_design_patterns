package br.curso.internetBanking;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxaContaInvestimentoVisitor extends TaxaContaAbstractVisitor {
	@Override
	public void visitContaInvestimento(ContaInvestimento contaInvestimento) {
		super.visitContaInvestimento(contaInvestimento);
		BigDecimal taxa = contaInvestimento.getSaldo().multiply(new BigDecimal(0.01)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
		contaInvestimento.taxar(taxa);
	}
}
